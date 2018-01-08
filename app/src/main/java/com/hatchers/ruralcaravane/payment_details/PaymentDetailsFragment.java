package com.hatchers.ruralcaravane.payment_details;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.hatchers.ruralcaravane.customer_registration.database.CustomerTable;
import com.hatchers.ruralcaravane.payment_details.database.PaymentDetailsHelper;
import com.hatchers.ruralcaravane.payment_details.database.PaymentTable;
import com.hatchers.ruralcaravane.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PaymentDetailsFragment extends Fragment {


    private static final String IMAGE_DIRECTORY = "/RuraralCaravane";
    private int CAMERA = 1;
    private int RESULT_CANCELED;

    private Toolbar payment_toolbar;
    private ImageButton payment_btnBack;
    private TextInputEditText payment_amount,advance_amount,remaining_amount;
    private ImageView takePhoto;
    private Button savePayment;


    PaymentTable paymentTable;

    public PaymentDetailsFragment() {
        // Required empty public constructor
    }



    private CustomerTable customertable;
    public static PaymentDetailsFragment getInstance(CustomerTable customertable)
    {
        PaymentDetailsFragment fragment = new PaymentDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CustomerTable.CUSTOMER_TABLE, customertable);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            customertable = getArguments().getParcelable(CustomerTable.CUSTOMER_TABLE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_payment_details, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(payment_toolbar);
        initializations(view);
        onClickListeners();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window =getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        File profile = new File(wallpaperDirectory, "profile.jpg");
        if(profile.exists())
        {
            Bitmap bitmap = BitmapFactory.decodeFile(profile.getAbsolutePath());
            takePhoto.setImageBitmap(bitmap);

        }
        else
        {
            //takePhoto.setImageResource(R.mipmap.receipt);

        }


        return view;
    }

    private void initializations(View view) {
        payment_toolbar = (Toolbar) view.findViewById(R.id.payment_toolbar);
        payment_btnBack = (ImageButton) view.findViewById(R.id.payment_btnBack);
        payment_amount = (TextInputEditText) view.findViewById(R.id.payment_amount);
        advance_amount = (TextInputEditText) view.findViewById(R.id.advance_amount);
        remaining_amount = (TextInputEditText) view.findViewById(R.id.remaining_amount);
        takePhoto = (ImageView) view.findViewById(R.id.takePhoto);
        savePayment = (Button) view.findViewById(R.id.savePayment);

    }
    private void onClickListeners()
    {
        payment_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        savePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPaymentDetailsData();
                if(checkValidation()) {

                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");

                    sweetAlertDialog.show();

                    if(PaymentDetailsHelper.insertPaymentDeatilsData(getContext(), paymentTable))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Payment Details Added Successfully");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                payment_amount.setText("");
                                advance_amount.setText("");
                                remaining_amount.setText("");
                                takePhoto.setImageResource(R.mipmap.receipt);
                            }
                        });
                    }
                    else
                    {

                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Payment Details Add Failed");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                    }
                }
            }
        });
    }


    private void showPictureDialog()
    {
        final CharSequence[] options = {"Take Photo", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera();
                                break;

                            case 2:
                                dialog.dismiss();
                        }
                    }
                });
        AlertDialog alert=builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    private void takePhotoFromCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            takePhoto.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, "profile.jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    private void setPaymentDetailsData()
    {
        paymentTable=new PaymentTable();

        paymentTable.setPayment_amountValue(payment_amount.getText().toString());
        paymentTable.setAdvance_amountValue(advance_amount.getText().toString());
        paymentTable.setReamaining_amountValue(remaining_amount.getText().toString());
    }

    private boolean checkValidation()
    {
        boolean response = true;

        if (payment_amount.getText().toString().trim().length() == 0) {
            payment_amount.setError("Please Enter Payment Amount");
            response = false;
        } else {
            payment_amount.setError(null);
        }

        if (advance_amount.getText().toString().trim().length() == 0) {
            advance_amount.setError("Please Enter Advance Amount");
            response = false;
        } else {
            advance_amount.setError(null);
        }

        if (remaining_amount.getText().toString().trim().length() == 0) {
            remaining_amount.setError("Please Enter Remaining Amount");
            response = false;
        } else {
            remaining_amount.setError(null);
        }

        return response;
    }

  /*  private void calculateAmount()
    {
        String totalCost= payment_amount.getText().toString();
        String advanceCost= advance_amount.getText().toString();

        int totalCostFinal=Integer.parseInt(totalCost);
        int advanceCostFinal=Integer.parseInt(advanceCost);

        Integer TotalAmount=totalCostFinal-advanceCostFinal;

        remaining_amount.setText(String.valueOf(TotalAmount));
    }*/
}
