package com.examapplication.ui.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.RunningNowModel;
import com.examapplication.ui.activities.SendToLoginActivity;
import com.examapplication.ui.fragments.AuthorDetailsFragment;
import com.examapplication.ui.fragments.ExamDetailsFragment;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;
import com.payu.india.Model.PaymentParams;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PayuHashes;
import com.payu.india.Payu.PayuConstants;
import com.payu.payuui.Activity.PayUBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Piyush on 26-07-2017.
 * Bynry
 */
public class RunningNowAdapter extends RecyclerView.Adapter<RunningNowAdapter.RunningNowHolder>
{

    public Context mContext;
    private ArrayList<RunningNowModel> runningNowList;
    private String userToken = "";
    private Intent intent;

    private PaymentParams mPaymentParams;
    private PayuConfig payuConfig;
    private String merchantKey = "", userCredentials = "";



    private final String key = "0MQaQP";// "gtKFFx";//put your key
    private final String salt = "13p0PXZk";// "eCwWELxi";//put your salt
    private final String PAYMENT_HASH = "payment_hash";
    private final String GET_MERCHANT_IBIBO_CODES_HASH = "get_merchant_ibibo_codes_hash";
    private final String VAS_FOR_MOBILE_SDK_HASH = "vas_for_mobile_sdk_hash";
    private final String PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK_HASH = "payment_related_details_for_mobile_sdk_hash";
    private final String DELETE_USER_CARD_HASH = "delete_user_card_hash";
    private final String GET_USER_CARDS_HASH = "get_user_cards_hash";
    private final String EDIT_USER_CARD_HASH = "edit_user_card_hash";
    private final String SAVE_USER_CARD_HASH = "save_user_card_hash";
    private final String CHECK_OFFER_STATUS_HASH = "check_offer_status_hash";
    private final String CHECK_ISDOMESTIC_HASH = "check_isDomestic_hash";
    private final String VERIFY_PAYMENT_HASH = "verify_payment_hash";





    public RunningNowAdapter(Context mContext, ArrayList<RunningNowModel> runningNowModels)
    {
    }

    public RunningNowAdapter(Context context, ArrayList<RunningNowModel> runningNowModels, String message)
    {
        this.mContext = context;
        this.runningNowList = runningNowModels;
    }


    @Override
    public RunningNowHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_running_now, null);
        RunningNowHolder viewHolder = new RunningNowHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RunningNowHolder holder, final int position)
    {

        userToken = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

        holder.txtExamName.setText(runningNowList.get(position).getExamName());
        holder.txtAuthorName.setText(runningNowList.get(position).getExamAuthor());
        holder.btnCourseName.setText(runningNowList.get(position).getExamCategory());
        holder.txtTotalMarks.setText(mContext.getString(R.string.total_marks)+" "+
                runningNowList.get(position).getExamTotalMarks());
        holder.txtMaxMarks.setText(mContext.getString(R.string.max_mark_s)+" "+
                runningNowList.get(position).getExamPassingMarks());
        holder.txtStartDate.setText(mContext.getString(R.string.start_date)+" "+
                runningNowList.get(position).getExamStartDate());
        holder.txtEndDate.setText(mContext.getString(R.string.end_date)+" "+
                runningNowList.get(position).getExamEndDate());
        holder.txtSaveRs.setText(mContext.getString(R.string.save_rs)+" "+
                runningNowList.get(position).getExamOfferPrice());
        holder.txtRs1.setText(runningNowList.get(position).getExamSalePrice());
        float pricePaid = Float.parseFloat(runningNowList.get(position).getExamSalePrice())
                - Float.parseFloat(runningNowList.get(position).getExamOfferPrice());
        holder.txtRs2.setText(mContext.getString(R.string.rs)+""+pricePaid);

        holder.txtAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AuthorDetailsFragment fragment = new AuthorDetailsFragment();
                fragment.getDetails(runningNowList, position);
                fragmentTransaction.add(android.R.id.content, fragment);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        holder.txtInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ExamDetailsFragment fragment = new ExamDetailsFragment();
                fragment.getDetails(runningNowList, position);
                fragmentTransaction.add(android.R.id.content, fragment);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        holder.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userToken.equals(""))
                {
                    intent = new Intent(mContext, SendToLoginActivity.class);
                    mContext.startActivity(intent);
                }
                else
                {
                    navigateToBaseActivity();
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        if(runningNowList != null && runningNowList.size() > 0){
            return runningNowList.size();
        }
        else{
            return 0;
        }
//        return 10;
    }


    public class RunningNowHolder extends RecyclerView.ViewHolder
    {
        public TextView txtExamName, txtAuthorName, txtTotalMarks, txtMaxMarks, txtInfo, txtReleasingDate, txtStartDate,
                txtEndDate, txtSaveRs, txtRs1, txtRs2, txtYear;
        public Button btnCourseName, btnBuyNow;
        public CardView cardViewPrice;

        public RunningNowHolder(View itemView)
        {
            super(itemView);
            txtExamName = (TextView)itemView.findViewById(R.id.txt_exam_name);
            txtAuthorName = (TextView)itemView.findViewById(R.id.txt_author_name);
            txtTotalMarks = (TextView)itemView.findViewById(R.id.txt_total_marks);
            txtMaxMarks = (TextView)itemView.findViewById(R.id.txt_max_marks);
            txtInfo = (TextView)itemView.findViewById(R.id.txt_info);
            txtReleasingDate = (TextView)itemView.findViewById(R.id.txt_releasing_date);
            txtStartDate = (TextView)itemView.findViewById(R.id.txt_start_date);
            txtEndDate = (TextView)itemView.findViewById(R.id.txt_end_date);
            txtSaveRs = (TextView)itemView.findViewById(R.id.txt_save_rs);
            txtRs1 = (TextView)itemView.findViewById(R.id.txt_rs_1);
            txtRs2 = (TextView)itemView.findViewById(R.id.txt_rs_2);
            txtYear = (TextView)itemView.findViewById(R.id.txt_year);

            btnCourseName = (Button) itemView.findViewById(R.id.btn_course_name);
            btnBuyNow = (Button)itemView.findViewById(R.id.btn_buy_now);
        }
    }

    public void navigateToBaseActivity() {

        merchantKey = "gtKFFx";

        int environment;
        environment = PayuConstants.STAGING_ENV;

        userCredentials = merchantKey + ":" + "piyush@abc.com";

        //TODO Below are mandatory params for hash genetation
        mPaymentParams = new PaymentParams();
        /**
         * For Test Environment, merchantKey = "gtKFFx"
         * For Production Environment, merchantKey should be your live key or for testing in live you can use "0MQaQP"
         */
        mPaymentParams.setKey(merchantKey);
        mPaymentParams.setAmount("10");
        mPaymentParams.setProductInfo("product_info");
        mPaymentParams.setFirstName("firstname");
        mPaymentParams.setEmail("xyz@gmail.com");

        /*
        * Transaction Id should be kept unique for each transaction.
        * */
        mPaymentParams.setTxnId("" + System.currentTimeMillis());

        /**
         * Surl --> Success url is where the transaction response is posted by PayU on successful transaction
         * Furl --> Failre url is where the transaction response is posted by PayU on failed transaction
         */
        mPaymentParams.setSurl("https://payu.herokuapp.com/success");
        mPaymentParams.setFurl("https://payu.herokuapp.com/failure");

        /*
         * udf1 to udf5 are options params where you can pass additional information related to transaction.
         * If you don't want to use it, then send them as empty string like, udf1=""
         * */
        mPaymentParams.setUdf1("udf1");
        mPaymentParams.setUdf2("udf2");
        mPaymentParams.setUdf3("udf3");
        mPaymentParams.setUdf4("udf4");
        mPaymentParams.setUdf5("udf5");

        /**
         * These are used for store card feature. If you are not using it then user_credentials = "default"
         * user_credentials takes of the form like user_credentials = "merchant_key : user_id"
         * here merchant_key = your merchant key,
         * user_id = unique id related to user like, email, phone number, etc.
         * */
        mPaymentParams.setUserCredentials(userCredentials);

        //TODO Pass this param only if using offer key
        //mPaymentParams.setOfferKey("cardnumber@8370");

        //TODO Sets the payment environment in PayuConfig object
        payuConfig = new PayuConfig();
        payuConfig.setEnvironment(environment);

        //TODO It is recommended to generate hash from server only. Keep your key and salt in server side hash generation code.
        generateHashFromServer(mPaymentParams);

        /**
         * Below approach for generating hash is not recommended. However, this approach can be used to test in PRODUCTION_ENV
         * if your server side hash generation code is not completely setup. While going live this approach for hash generation
         * should not be used.
         * */
//        String salt = "13p0PXZk";
//        generateHashFromSDK(mPaymentParams, salt);

    }

    protected String concatParams(String key, String value) {
        return key + "=" + value + "&";
    }

    public void generateHashFromServer(PaymentParams mPaymentParams) {
        //nextButton.setEnabled(false); // lets not allow the user to click the button again and again.

        // lets create the post params
        StringBuffer postParamsBuffer = new StringBuffer();
        postParamsBuffer.append(concatParams(PayuConstants.KEY, mPaymentParams.getKey()));
        postParamsBuffer.append(concatParams(PayuConstants.AMOUNT, mPaymentParams.getAmount()));
        postParamsBuffer.append(concatParams(PayuConstants.TXNID, mPaymentParams.getTxnId()));
        postParamsBuffer.append(concatParams(PayuConstants.EMAIL, null == mPaymentParams.getEmail() ? "" : mPaymentParams.getEmail()));
        postParamsBuffer.append(concatParams(PayuConstants.PRODUCT_INFO, mPaymentParams.getProductInfo()));
        postParamsBuffer.append(concatParams(PayuConstants.FIRST_NAME, null == mPaymentParams.getFirstName() ? "" : mPaymentParams.getFirstName()));
        postParamsBuffer.append(concatParams(PayuConstants.UDF1, mPaymentParams.getUdf1() == null ? "" : mPaymentParams.getUdf1()));
        postParamsBuffer.append(concatParams(PayuConstants.UDF2, mPaymentParams.getUdf2() == null ? "" : mPaymentParams.getUdf2()));
        postParamsBuffer.append(concatParams(PayuConstants.UDF3, mPaymentParams.getUdf3() == null ? "" : mPaymentParams.getUdf3()));
        postParamsBuffer.append(concatParams(PayuConstants.UDF4, mPaymentParams.getUdf4() == null ? "" : mPaymentParams.getUdf4()));
        postParamsBuffer.append(concatParams(PayuConstants.UDF5, mPaymentParams.getUdf5() == null ? "" : mPaymentParams.getUdf5()));
        postParamsBuffer.append(concatParams(PayuConstants.USER_CREDENTIALS, mPaymentParams.getUserCredentials() == null ? PayuConstants.DEFAULT : mPaymentParams.getUserCredentials()));

        // for offer_key
        if (null != mPaymentParams.getOfferKey())
            postParamsBuffer.append(concatParams(PayuConstants.OFFER_KEY, mPaymentParams.getOfferKey()));

        String postParams = postParamsBuffer.charAt(postParamsBuffer.length() - 1) == '&' ? postParamsBuffer.substring(0, postParamsBuffer.length() - 1).toString() : postParamsBuffer.toString();

        // lets make an api call
        GetHashesFromServerTask getHashesFromServerTask = new GetHashesFromServerTask();
        getHashesFromServerTask.execute(postParams);
    }

    private class GetHashesFromServerTask extends AsyncTask<String, String, PayuHashes> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected PayuHashes doInBackground(String... postParams) {
            PayuHashes payuHashes = new PayuHashes();
            try {

                //TODO Below url is just for testing purpose, merchant needs to replace this with their server side hash generation url
                //TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
                URL url = new URL("https://payu.herokuapp.com/get_hash");

                // get the payuConfig first
                String postParam = postParams[0];

                byte[] postParamsByte = postParam.getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postParamsByte.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postParamsByte);

                InputStream responseInputStream = conn.getInputStream();
                StringBuffer responseStringBuffer = new StringBuffer();
                byte[] byteContainer = new byte[1024];
                for (int i; (i = responseInputStream.read(byteContainer)) != -1; ) {
                    responseStringBuffer.append(new String(byteContainer, 0, i));
                }

                JSONObject response = new JSONObject(responseStringBuffer.toString());

                Iterator<String> payuHashIterator = response.keys();
                while (payuHashIterator.hasNext()) {
                    String key = payuHashIterator.next();
                    switch (key) {
                        //TODO Below three hashes are mandatory for payment flow and needs to be generated at merchant server
                        /**
                         * Payment hash is one of the mandatory hashes that needs to be generated from merchant's server side
                         * Below is formula for generating payment_hash -
                         *
                         * sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT)
                         *
                         */
                        case "payment_hash":
                            payuHashes.setPaymentHash(response.getString(key));
                            break;
                        /**
                         * vas_for_mobile_sdk_hash is one of the mandatory hashes that needs to be generated from merchant's server side
                         * Below is formula for generating vas_for_mobile_sdk_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be "default"
                         *
                         */
                        case "vas_for_mobile_sdk_hash":
                            payuHashes.setVasForMobileSdkHash(response.getString(key));
                            break;
                        /**
                         * payment_related_details_for_mobile_sdk_hash is one of the mandatory hashes that needs to be generated from merchant's server side
                         * Below is formula for generating payment_related_details_for_mobile_sdk_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
                         *
                         */
                        case "payment_related_details_for_mobile_sdk_hash":
                            payuHashes.setPaymentRelatedDetailsForMobileSdkHash(response.getString(key));
                            break;

                        //TODO Below hashes only needs to be generated if you are using Store card feature
                        /**
                         * delete_user_card_hash is used while deleting a stored card.
                         * Below is formula for generating delete_user_card_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
                         *
                         */
                        case "delete_user_card_hash":
                            payuHashes.setDeleteCardHash(response.getString(key));
                            break;
                        /**
                         * get_user_cards_hash is used while fetching all the cards corresponding to a user.
                         * Below is formula for generating get_user_cards_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
                         *
                         */
                        case "get_user_cards_hash":
                            payuHashes.setStoredCardsHash(response.getString(key));
                            break;
                        /**
                         * edit_user_card_hash is used while editing details of existing stored card.
                         * Below is formula for generating edit_user_card_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
                         *
                         */
                        case "edit_user_card_hash":
                            payuHashes.setEditCardHash(response.getString(key));
                            break;
                        /**
                         * save_user_card_hash is used while saving card to the vault
                         * Below is formula for generating save_user_card_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
                         *
                         */
                        case "save_user_card_hash":
                            payuHashes.setSaveCardHash(response.getString(key));
                            break;

                        //TODO This hash needs to be generated if you are using any offer key
                        /**
                         * check_offer_status_hash is used while using check_offer_status api
                         * Below is formula for generating check_offer_status_hash -
                         *
                         * sha512(key|command|var1|salt)
                         *
                         * here, var1 will be Offer Key.
                         *
                         */
                        case "check_offer_status_hash":
                            payuHashes.setCheckOfferStatusHash(response.getString(key));
                            break;
                        default:
                            break;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return payuHashes;
        }

        @Override
        protected void onPostExecute(PayuHashes payuHashes) {
            super.onPostExecute(payuHashes);

            progressDialog.dismiss();
            launchSdkUI(payuHashes);
        }
    }

    private void generateHashFromSDK(PaymentParams mPaymentParams, String salts)
    {
        JSONObject response = new JSONObject();

        String ph = checkNull(key) + "|" + checkNull(PayuConstants.TXNID) + "|" + checkNull("10") + "|" + checkNull("Exam")
                + "|" + checkNull("Piyush") + "|" + checkNull("piyush@abc.com") + "|" + checkNull(PayuConstants.UDF1) + "|" + checkNull(PayuConstants.UDF2)
                + "|" + checkNull(PayuConstants.UDF3) + "|" + checkNull(PayuConstants.UDF4) + "|" + checkNull(PayuConstants.UDF5) + "||||||" + salt;
        try {
            String paymentHash = getSHA(ph);
            response.put(PAYMENT_HASH, paymentHash);
            response.put(GET_MERCHANT_IBIBO_CODES_HASH, generateHashString("get_merchant_ibibo_codes", "default"));
            response.put(VAS_FOR_MOBILE_SDK_HASH, generateHashString("vas_for_mobile_sdk", "default"));
            response.put(PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK_HASH,
                    generateHashString("payment_related_details_for_mobile_sdk", "default"));

            //for verify payment (optional)
            if(!checkNull(PayuConstants.TXNID).isEmpty()) {
                response.put(VERIFY_PAYMENT_HASH,
                        generateHashString("verify_payment", PayuConstants.TXNID));
            }

            if(!checkNull(PayuConstants.USER_CREDENTIALS).isEmpty()) {
                response.put(DELETE_USER_CARD_HASH, generateHashString("delete_user_card", PayuConstants.USER_CREDENTIALS));
                response.put(GET_USER_CARDS_HASH, generateHashString("get_user_cards", PayuConstants.USER_CREDENTIALS));
                response.put(EDIT_USER_CARD_HASH, generateHashString("edit_user_card", PayuConstants.USER_CREDENTIALS));
                response.put(SAVE_USER_CARD_HASH, generateHashString("save_user_card", PayuConstants.USER_CREDENTIALS));
                response.put(PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK_HASH,
                        generateHashString("payment_related_details_for_mobile_sdk", PayuConstants.USER_CREDENTIALS));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        /*// check_offer_status
        if (!checkNull(offerKey).isEmpty()) {
            response.put(CHECK_OFFER_STATUS_HASH, generateHashString("check_offer_status", offerKey));
        }

        // check_isDomestic
        if (!checkNull(cardBin).isEmpty()) {
            response.put(CHECK_ISDOMESTIC_HASH, generateHashString("check_isDomestic", cardBin));
        }*/
    }

    private String generateHashString(String command, String var1) {
        return getSHA(key + "|" + command + "|" + var1 + "|" + salt);
    }

    private String checkNull(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }

    private String getSHA(String str) {

        MessageDigest md;
        String out = "";
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(str.getBytes());
            byte[] mb = md.digest();

            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return out;

    }

    private void launchSdkUI(PayuHashes payuHashes)
    {
        intent = new Intent(mContext, PayUBaseActivity.class);
        intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig);
        intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams);
        intent.putExtra(PayuConstants.PAYU_HASHES, payuHashes);
        mContext.startActivity(intent);
    }
}