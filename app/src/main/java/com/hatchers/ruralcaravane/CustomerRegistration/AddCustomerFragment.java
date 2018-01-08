package com.hatchers.ruralcaravane.CustomerRegistration;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTable;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTableHelper;
import com.hatchers.ruralcaravane.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddCustomerFragment extends Fragment {

    private static final String IMAGE_DIRECTORY = "/RuraralCaravane";
    private int CAMERA = 1,GALLERY=2;

    CustomerTable customer_table;
    private String selected_gender = "";
    private Button save,ScanByAadhar;
    private int RESULT_CANCELED;
    private ImageView profileImage;
    private FloatingActionButton fab;
    private RadioGroup radioGroupGender;
    private RadioButton male, female;
    private TextView uniqueIdTxt;
    private EditText customer_name, village_name, customer_address, customer_mobileno, customer_age;


    public AddCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__customer, container, false);

        initializations(view);
        generateUniqueId();
        setGender();
        onclicklisteners();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
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
            profileImage.setImageBitmap(bitmap);



            // to set the background color (color should have some alpha val)
            //  diagonalView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            // to make the solid color diagonal
            //  diagonalView.setSolidColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        else
        {
            profileImage.setImageResource(R.drawable.user_profile);

        }


    return view;
    }


    private void initializations(View view)
    {

        save = (Button) view.findViewById(R.id.saveBtn);
        customer_name = (EditText) view.findViewById(R.id.customer_name);
        village_name = (EditText) view.findViewById(R.id.village_name);
        customer_address = (EditText) view.findViewById(R.id.customer_address);
        customer_mobileno = (EditText) view.findViewById(R.id.customer_mobileno);
        customer_age = (EditText) view.findViewById(R.id.customer_age);
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radio_gender);
        male = (RadioButton) view.findViewById(R.id.male);
        female = (RadioButton) view.findViewById(R.id.female);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        profileImage=(ImageView)view.findViewById(R.id.profileImage);
        uniqueIdTxt=(TextView)view.findViewById(R.id.uniqueIdTxt);
        ScanByAadhar=(Button)view.findViewById(R.id.ScanByAadhar);

    }

    private void onclicklisteners()
    {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCustomerData();
                if(checkValidation()) {
                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");
                    sweetAlertDialog.show();

                    if(CustomerTableHelper.insertCustomerData(getContext(), customer_table))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Customer Data Added Successfully");
                        sweetAlertDialog.setConfirmText("Ok");

                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                profileImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
                                customer_name.setText("");
                                village_name.setText("");
                                customer_address.setText("");
                                customer_mobileno.setText("");
                                customer_age.setText("");
                                uniqueIdTxt.setText("");
                                male.setChecked(false);
                                female.setChecked(false);

                            }
                        });

                    }
                    else
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Customer Data Add Failed");
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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
    }

    private void generateUniqueId()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        uniqueIdTxt.setText("CU"+datetime);
    }


    private void showPictureDialog()
    {
        final CharSequence[] options = {"Take Photo","Choose Photo from Gallery", "Cancel"};

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

                            case 1:
                                choosePhotoFromGallary();
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


    public void choosePhotoFromGallary()
    {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    profileImage.setImageBitmap(bitmap);
                   // diagonalView.setImageBitmap(bitmap);
                    //diagonalView.setBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(thumbnail);
            saveImage(thumbnail);
           // diagonalView.setImageBitmap(thumbnail);
            //diagonalView.setBitmap(thumbnail);


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

    private boolean setCustomerData()
    {

        customer_table = new CustomerTable();

        customer_table.setCustomerNameValue(customer_name.getText().toString());
        customer_table.setVillageNameValue(village_name.getText().toString());
        customer_table.setCustomerAddressValue(customer_address.getText().toString());
        customer_table.setCustomerMobilenoValue(customer_mobileno.getText().toString());
        customer_table.setCustomerAgeValue(customer_age.getText().toString());
        customer_table.setCustomerGenderValue(selected_gender);
        customer_table.setUniqueIdValue(uniqueIdTxt.getText().toString());

        return false;
    }

    public void setGender()
    {
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    selected_gender = "M";
                } else if (checkedId == R.id.female) {
                    selected_gender = "F";
                }
            }
        });

    }


    private boolean checkValidation()
    {
        boolean response = true;

        if (customer_name.getText().toString().trim().length() == 0) {
            customer_name.setError("Please Enter Customer Name");
            response = false;
        } else {
            customer_name.setError(null);
        }

        if (customer_age.getText().toString().trim().length() == 0) {
            customer_age.setError("Please Enter Customer Age ");
            response = false;
        } else {
            customer_age.setError(null);
        }

        if (customer_address.getText().toString().trim().length() == 0) {
            customer_address.setError("Please Enter Customer Address ");
            response = false;
        } else {
            customer_address.setError(null);
        }

        if (customer_mobileno.getText().toString().trim().length() == 0) {
            customer_mobileno.setError("Please Enter Customer Mobile Number");
            response = false;
        } else {
            customer_mobileno.setError(null);
        }

        if (village_name.getText().toString().trim().length() == 0) {
            village_name.setError("Please Enter Village Name");
            response = false;
        } else {
            village_name.setError(null);
        }

        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please Select Gender", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

        }
        return response;
    }
}
