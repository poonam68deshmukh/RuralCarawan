package com.hatchers.ruralcaravane.payment_details;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hatchers.ruralcaravane.R;
import com.hatchers.ruralcaravane.customer_registration.database.CustomerTable;
import com.hatchers.ruralcaravane.file.FileHelper;
import com.hatchers.ruralcaravane.file.Folders;
import com.hatchers.ruralcaravane.payment_details.database.PaymentDetailsHelper;
import com.hatchers.ruralcaravane.payment_details.database.PaymentTable;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.hatchers.ruralcaravane.current_date_time_function.CurrentDateTime.getCurrentDateTime;

public class PaymentDetailsFragment extends Fragment {



    private int CAMERA = 1;
    private int RESULT_CANCELED;
    Bitmap payBitmap;
    private Toolbar payment_toolbar;
    private ImageView payment_btnBack;
    private TextInputEditText payment_amount,paid_amount,remaining_amount;
    private ImageView takePhoto;
    private Button savePayment;
    private String paymentUniqueId;


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
        addTextListner();
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window =getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        return view;
    }

    private void initializations(View view) {
        payment_toolbar = (Toolbar) view.findViewById(R.id.payment_toolbar);
        payment_btnBack = (ImageView) view.findViewById(R.id.payment_btnBack);
        payment_amount = (TextInputEditText) view.findViewById(R.id.payment_amount);
        paid_amount = (TextInputEditText) view.findViewById(R.id.paid_amount);
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
                    FileHelper.savePNGImage(Folders.CHULHAFOLDER,payBitmap,"PAY_"+paymentTable.getPaymentUniqueIdValue());
                    if(PaymentDetailsHelper.insertPaymentDetailsData(getContext(), paymentTable))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Payment Details Added Successfully");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                payment_amount.setText("");
                                paid_amount.setText("");
                                remaining_amount.setText("");
                                takePhoto.setImageResource(R.mipmap.receipt);
                                payBitmap=null;
                                getActivity().onBackPressed();
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
            payBitmap=thumbnail;
            //Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setPaymentDetailsData()
    {
        paymentTable=new PaymentTable();

        paymentTable.setPayment_amountValue(payment_amount.getText().toString());
        paymentTable.setTotalPaidValue(paid_amount.getText().toString());
        paymentTable.setRemaining_amountValue(remaining_amount.getText().toString());
        paymentTable.setDateOfPaymentValue(getCurrentDateTime());
        paymentTable.setUpdateDateValue(getCurrentDateTime());
        paymentTable.setUpload_statusValue("0");
        paymentTable.setPaymentUniqueIdValue(generateUniqueId());

    }

    private void calculateRemainingAmount()
    {
        int remainingAmount;
        if(payment_amount.getText().toString().equalsIgnoreCase("") ||
                paid_amount.getText().toString().equalsIgnoreCase(""))
        {
            remainingAmount= 0;
            remaining_amount.setText(String.valueOf(remainingAmount));
        }
        else
        {
            remainingAmount= Integer.parseInt(payment_amount.getText().toString())- Integer.parseInt(paid_amount.getText().toString());
            remaining_amount.setText(String.valueOf(remainingAmount));
        }

    }

    private void addTextListner()
    {
        payment_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRemainingAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateRemainingAmount();
            }
        });

        paid_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateRemainingAmount();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateRemainingAmount();

            }
        });
    }


    private String generateUniqueId()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        paymentUniqueId="PAY"+datetime;

        return "" ;
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

        if (paid_amount.getText().toString().trim().length() == 0) {
            paid_amount.setError("Please Enter Advance Amount");
            response = false;
        } else {
            paid_amount.setError(null);
        }

        if (remaining_amount.getText().toString().trim().length() == 0) {
            remaining_amount.setError("Please Enter Remaining Amount");
            response = false;
        } else {
            remaining_amount.setError(null);
        }

        return response;
    }

  }
