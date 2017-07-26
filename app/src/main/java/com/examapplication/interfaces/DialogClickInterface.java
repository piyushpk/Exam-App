package com.examapplication.interfaces;

import android.content.DialogInterface;

public interface DialogClickInterface {

    void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier);

    void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier);
}
