package com.hatchers.ruralcaravane.KitchenSuitability;

import android.app.AlertDialog;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTable;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTableHelper;
import com.hatchers.ruralcaravane.PaymentDetails.PaymentDetailsFragment;
import com.hatchers.ruralcaravane.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class kitchenSuitabilityFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {


    private static final String IMAGE_DIRECTORY = "/RuraralCaravane";
    private int CAMERA = 1;

    private Toolbar kitchen_toolbar;
    private ImageButton kitchen_btnBack;
    private Spinner house_type, roof_type;
    private TextInputEditText kitchen_height;
    private ImageView takePicture;
    private int RESULT_CANCELED;
    private Button upload;
    KitchenTable kitchen_table;
    FrameLayout layout;
    private GoogleApiClient client;
    private TextView kitchenUniqueIdText;

    public kitchenSuitabilityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kitchen__suitability, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(kitchen_toolbar);

        initializations(view);
        onclicklisteners();
        generateUniqueId();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).build();

        house_type.setOnItemSelectedListener(this);
        roof_type.setOnItemSelectedListener(this);
        layout = (FrameLayout)view. findViewById(R.id.AddreFramelauout);


        ArrayAdapter<CharSequence> house_survey_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.House_Type, android.R.layout.simple_spinner_item);
        house_survey_adapter.setDropDownViewResource(R.layout.spinner_item);
        house_type.setAdapter(house_survey_adapter);

        ArrayAdapter<CharSequence> roof_type_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Roof_Type, android.R.layout.simple_spinner_item);
        roof_type_adapter.setDropDownViewResource(R.layout.spinner_item);
        roof_type.setAdapter(roof_type_adapter);


        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        File profile = new File(wallpaperDirectory, "Area_Picture.jpg");
        if (profile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(profile.getAbsolutePath());
            takePicture.setImageBitmap(bitmap);


            // to set the background color (color should have some alpha val)
            //  diagonalView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            // to make the solid color diagonal
            //  diagonalView.setSolidColor(getResources().getColor(R.color.colorPrimaryDark));

        } else {
            takePicture.setImageResource(R.mipmap.take_picture_image);

        }


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }
        return view;
    }


    private void initializations(View view) {
        kitchen_toolbar = (Toolbar) view.findViewById(R.id.kitchen_toolbar);
        kitchen_btnBack = (ImageButton) view.findViewById(R.id.kitchen_btnBack);
        house_type = (Spinner) view.findViewById(R.id.house_type_survey);
        roof_type = (Spinner) view.findViewById(R.id.roof_type);
        kitchen_height = (TextInputEditText) view.findViewById(R.id.kitchen_height);
        takePicture = (ImageView) view.findViewById(R.id.takePicture);
        upload = (Button) view.findViewById(R.id.upload);
        kitchenUniqueIdText=(TextView)view.findViewById(R.id.kitchenUniqueIdText);


    }

    private void onclicklisteners() {
        kitchen_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKitchenData();
                if(checkValidation()) {
                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");
                    sweetAlertDialog.show();


                    if(KitchenTableHelper.insertKitchenData(getContext(), kitchen_table))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Kitchen Data Added Successfully");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                house_type.setAdapter(null);
                                roof_type.setAdapter(null);
                                kitchen_height.setText("");
                                takePicture.setImageResource(R.mipmap.chullha);


                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                AddKitchenAddress paymentDetailsFragment=new AddKitchenAddress();
                                fragmentTransaction.replace(R.id.frame_layout,paymentDetailsFragment).addToBackStack(null).commit();

                            }
                        });
                    }

                    else
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Kitchen Data Add Failed");
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


    private void setKitchenData()
    {

        kitchen_table = new KitchenTable();

        kitchen_table.setHouse_typeValue(house_type.getSelectedItem().toString());
        kitchen_table.setRoof_typeValue(roof_type.getSelectedItem().toString());
        kitchen_table.setKitchen_heightValue(kitchen_height.getText().toString());
        kitchen_table.setKitchenUniqueIdValue(kitchenUniqueIdText.getText().toString());

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
        AlertDialog alert = builder.create();
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
            takePicture.setImageBitmap(thumbnail);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    private boolean checkValidation()
    {
        boolean response = true;

        if (kitchen_height.getText().toString().trim().length() == 0) {
            kitchen_height.setError("Please Enter Kitchen Height");
            response = false;
        } else {
            kitchen_height.setError(null);
        }

        if (house_type.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select House Type")) {

            View selectedView = house_type.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (house_type.getSelectedItemPosition() == 0) {
                    String errorString = "Please Select House Type";
                    selectedTextView.setError(errorString);

                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }


            if (roof_type.getSelectedItem().toString().trim().equalsIgnoreCase("Please Enter Roof Type")) {

               View selectedView1 = roof_type.getSelectedView();
                if (selectedView1 != null && selectedView1 instanceof TextView) {
                    TextView selectedTextView = (TextView) selectedView1;
                    if (roof_type.getSelectedItemPosition() == 0) {
                        String errorString = "Please Enter Roof Type";
                        selectedTextView.setError(errorString);

                    } else {
                        selectedTextView.setError(null);
                    }
                }

                response = false;
            }



        return response;
    }


    private void generateUniqueId()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        kitchenUniqueIdText.setText("KIT"+datetime);
    }

}
