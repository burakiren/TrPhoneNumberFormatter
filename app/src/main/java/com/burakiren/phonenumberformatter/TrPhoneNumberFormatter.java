package com.burakiren.phonenumberformatter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by burakiren on 19/04/16.
 */
public class TrPhoneNumberFormatter implements TextWatcher {


    private EditText etNumber;

    private boolean backspacingFlag = false;
    private boolean editedFlag = false;
    private int cursorComplement;

    public TrPhoneNumberFormatter(EditText etNumber) {
        this.etNumber = etNumber;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        cursorComplement = s.length() - etNumber.getSelectionStart();
        if (count > after) {
            backspacingFlag = true;
        } else {
            backspacingFlag = false;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Bu kısım bos kalacak.
    }


    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();
        String phone = string.replaceAll("[^\\d]", "");

        if (!editedFlag) {


            if (phone.length() >= 6 && !backspacingFlag) {
                editedFlag = true;
                String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + " " + phone.substring(6);
                etNumber.setText(ans);
                etNumber.setSelection(etNumber.getText().length() - cursorComplement);

            } else if (phone.length() >= 3 && !backspacingFlag) {
                editedFlag = true;
                String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
                etNumber.setText(ans);
                etNumber.setSelection(etNumber.getText().length() - cursorComplement);
            }
        } else {
            editedFlag = false;
        }
    }

}
