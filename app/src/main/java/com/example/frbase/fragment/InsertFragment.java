package com.example.frbase.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frbase.R;
import com.example.frbase.model.Image;

import java.util.ArrayList;
import java.util.List;


public class InsertFragment extends Fragment {
    private EditText nameEdit, descriptionEdit;
    private ImageView blankImage;
    private RecyclerView recyclerView;
    private Button saveButton, uploadButton;
    private int counter=0;
    public static final int IMAGE_CODE=1;
    private List<Image> imageNameList;
    private List<String> savedImageUri;


    public InsertFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_insert, container, false);
        nameEdit=view.findViewById(R.id.enterNoteId);
        descriptionEdit = view.findViewById(R.id.noteDescriptionId);
        blankImage=view.findViewById(R.id.blankImageId);
        saveButton=view.findViewById(R.id.saveButtonId);
        uploadButton=view.findViewById(R.id.uploadButtonId);
        recyclerView=view.findViewById(R.id.uploadRecycleId);
        imageNameList =new ArrayList<>();
        savedImageUri =new ArrayList<>();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/+");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(intent,IMAGE_CODE);


            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageNameList.clear();
        savedImageUri.clear();
        getActivity();

        if(requestCode == IMAGE_CODE && resultCode==Activity.RESULT_OK){
            if(data.getClipData() != null){

            }
            else if(data.getData() != null){

            }
        }

    }
}