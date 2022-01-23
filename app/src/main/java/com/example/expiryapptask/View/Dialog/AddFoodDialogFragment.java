package com.example.expiryapptask.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.expiryapptask.R;
import com.example.expiryapptask.database.ItemDataBase;
import com.example.expiryapptask.pojo.ItemModel;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class AddFoodDialogFragment extends DialogFragment {

    public static AlertDialog alertDialog;
    public ItemDataBase dataBase;
    ItemModel itemModel;


    private EditText nameEditText;
    private Spinner categorySpinner;
    private ImageView calendarImageView;
    private ImageView cameraImageView;
    ScanCodeActivity barCodeActivity;
    public static TextView id ;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        dataBase = ItemDataBase.getInstance(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.add_food_dialog, null);

        //find views from resources

        nameEditText = (EditText) dialogLayout.findViewById(R.id.edit_text_food_name_dialog);
        categorySpinner = (Spinner) dialogLayout.findViewById(R.id.spinner_category);
        calendarImageView = (ImageView) dialogLayout.findViewById(R.id.calendar_image_view);
        cameraImageView = (ImageView) dialogLayout.findViewById(R.id.camera_image_view);
        id = dialogLayout.findViewById(R.id.date_et);


        cameraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ScanCodeActivity.class));
            }
        });




        builder.setView(dialogLayout)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String itemName = nameEditText.getText().toString();
                        String itemCategory =  categorySpinner.getSelectedItem().toString();
                        String itemId = id.getText().toString();
                        long HourInMillis = 3600000;
                        if (itemName != null && !nameEditText.getText().toString().isEmpty()){
                            dataBase.postsDao().insertItem(new ItemModel(itemId,itemName,itemCategory,HourInMillis,0))
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(@NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {

                                        }
                                    });

                        }else {
                            Toast.makeText(getContext(), "You should enter item's name", Toast.LENGTH_SHORT).show();
                        }



                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddFoodDialogFragment.this.getDialog().cancel();
            }
        })
                .setTitle("Add Food");

        alertDialog = builder.create();
        return alertDialog;
    }

    public interface onFragmentListener{
        void onFragmentInteraction(ItemModel itemModel);
    }

}
