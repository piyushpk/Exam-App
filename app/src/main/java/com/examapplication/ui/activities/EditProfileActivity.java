package com.examapplication.ui.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examapplication.R;
import com.examapplication.interfaces.ApiServiceCaller;
import com.examapplication.models.CategoryListModel;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;
import com.examapplication.utility.CommonUtility;
import com.examapplication.utility.PermissionUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends ParentActivity implements View.OnClickListener, ApiServiceCaller
{
    private Context mContext;
    private CircleImageView imgProfile;
    private ImageView imgBack, imgSave, imgSetPhoto;
    private TextView txtTitle;
    private EditText edtFirstName, edtLastName, edtEmail, edtPhoneNo;
    private String userFirstName = "", userLastName = "", userEmail = "", userMobileNo = "",
            userCity = "", userAddress = "", token = "", userImage = "";
    private RelativeLayout relativeImage;
    private Spinner spinnerLocation;
    private PermissionUtility permissionUtility;
    private Bitmap bitmap;
    private String profileImage = "";
    private ArrayList<String> spinnerArrayName = new ArrayList<>();
    private ArrayList<String> spinnerArrayId = new ArrayList<>();
    private ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mContext = this;

        userFirstName = AppPreferences.getInstance(mContext).getString(AppConstants.USER_FIRST_NAME, "");
        userLastName = AppPreferences.getInstance(mContext).getString(AppConstants.USER_LAST_NAME, "");
        userImage = AppPreferences.getInstance(mContext).getString(AppConstants.USER_IMAGE, "");
        userEmail = AppPreferences.getInstance(mContext).getString(AppConstants.USER_EMAIL, "");
        userMobileNo = AppPreferences.getInstance(mContext).getString(AppConstants.USER_MOBILE, "");
        userAddress = AppPreferences.getInstance(mContext).getString(AppConstants.USER_ADDRESS, "");
        userCity = AppPreferences.getInstance(mContext).getString(AppConstants.USER_CITY, "");
        token = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        imgSave = (ImageView)findViewById(R.id.img_save);
        imgSave.setOnClickListener(this);
        imgSetPhoto = (ImageView)findViewById(R.id.img_set_photo);
        imgSetPhoto.setOnClickListener(this);

        txtTitle = (TextView)findViewById(R.id.txt_title);
        txtTitle.setText(getString(R.string.my_profile));

        edtFirstName = (EditText)findViewById(R.id.edt_first_name);
        edtFirstName.setText(userFirstName);
        edtLastName = (EditText)findViewById(R.id.edt_last_name);
        edtLastName.setText(userLastName);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtEmail.setText(userEmail);
        edtPhoneNo = (EditText)findViewById(R.id.edt_phone_no);
        edtPhoneNo.setText(userMobileNo);

        imgProfile = (CircleImageView)findViewById(R.id.img_profile);

        if(userImage.equals("") || userImage.isEmpty() || userImage == null)
        {}
        else
        {
            Picasso.with(mContext)
                   .load(userImage)
                   .into(imgProfile);
        }

        relativeImage = (RelativeLayout)findViewById(R.id.relative_image);

        spinnerLocation = (Spinner)findViewById(R.id.spinner_location);

        permissionUtility = new PermissionUtility(this);

    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }

        if(v == imgSave)
        {

        }

        if(v == imgSetPhoto)
        {
            showImageOptionsDialog();
        }
    }

    private void showImageOptionsDialog() {
        final String CHOOSE_GALLERY = "Choose Gallery", USE_CAMERA = "Use Camera";
        ArrayList<String> list = new ArrayList<String>();
        final CharSequence items[];
        list.add(CHOOSE_GALLERY);
        list.add(USE_CAMERA);

        items = list.toArray(new CharSequence[list.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Profile Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(USE_CAMERA)) {

                    if (permissionUtility.checkPermissionForCamera()) {

                        if (permissionUtility.checkPermissionForExternalStorage()) {
                            try {
                                Intent cameraIntent = new Intent(android.provider.
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, AppConstants.CAPTURE_IMAGE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            permissionUtility.requestPermissionForExternalStorage();
                        }
                    } else {
                        permissionUtility.requestPermissionForCamera();
                    }
                } else if (items[item].equals(CHOOSE_GALLERY)) {
                    if (permissionUtility.checkPermissionForReadExternalStorage()) {
                        if (permissionUtility.checkPermissionForExternalStorage()) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select file"), AppConstants
                                        .SELECT_FILE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            permissionUtility.checkPermissionForExternalStorage();
                        }
                    } else {
                        permissionUtility.requestPermissionForReadExternalStorage();
                    }
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {
        super.onActivityResult(pRequestCode, pResultCode, pData);
        if (pRequestCode == AppConstants.SELECT_FILE) {
            try {
                Uri selectedImage = pData.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(picturePath);
                Bitmap photo = bitmap;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteUserImage = byteArrayOutputStream.toByteArray();
                profileImage = Base64.encodeToString(byteUserImage, Base64.DEFAULT);
                imgProfile.setImageBitmap(photo);
                relativeImage.setBackground(new BitmapDrawable(getResources(), photo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (pRequestCode == AppConstants.CAPTURE_IMAGE) {
            if (pData == null) {
            } else {
                Bitmap photo = (Bitmap) pData.getExtras().get("data");

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), photo);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                File finalFile = new File(getRealPathFromURI(tempUri));
                Uri yourUri = Uri.fromFile(finalFile);
                if (yourUri == null)
                    return;

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteUserImage = byteArrayOutputStream.toByteArray();
                profileImage = Base64.encodeToString(byteUserImage, Base64.DEFAULT);
                imgProfile.setImageBitmap(photo);
                relativeImage.setBackground(new BitmapDrawable(getResources(), photo));
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCityList();
    }

    private void getCityList()
    {
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_CITY_LIST_URL, ApiConstants.GET_CITY_LIST, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_CITY_LIST);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(mContext, getString(R.string.internet_failed), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserDetails()
    {
        String fName = edtFirstName.getText().toString().trim();
        String lName = edtLastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String mobile = edtPhoneNo.getText().toString().trim();
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name", fName);
                jsonObject.put("last_name", lName);
                jsonObject.put("contact_no", mobile);
                jsonObject.put("city", fName);

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_CATEGORY_URL, ApiConstants.GET_CATEGORY, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_CATEGORY);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(mContext, getString(R.string.internet_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label)
    {
        switch (label)
        {
            case ApiConstants.GET_CITY_LIST:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            categoryListModels.addAll(jsonResponse.city);
                            for(int i=0; i < categoryListModels.size(); i++)
                            {
                                spinnerArrayName.add(categoryListModels.get(i).getCityName());
                                spinnerArrayId.add(categoryListModels.get(i).getCityId());
                            }
                            spinnerList(spinnerArrayName, spinnerArrayId);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response)
    {
        dismissLoadingDialog();
        switch (label)
        {
            case ApiConstants.GET_CITY_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onAsyncCompletelyFail(String message, String label)
    {
        dismissLoadingDialog();
        switch (label)
        {
            case ApiConstants.GET_CITY_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    public void spinnerList(final ArrayList<String> cityName, final ArrayList<String> cityId)
    {
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, cityName)
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(CommonUtility.getColor(getContext(), R.color.colorBlack));
                ((TextView) v).setTextSize(15f);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTextSize(15f);
                return v;
            }
        };
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapterLocation);
//        spinnerLocation.setSelection(Integer.parseInt(userCity));

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position,
                    long id)
            {
                String item = cityId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {}

        });

    }
}
