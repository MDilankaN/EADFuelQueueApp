package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.database.DBHandler;
import com.example.myapplication.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button Profile, Station, Staff, Queue, ContactUs, Privacy;
    String username,type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        Profile = view.findViewById(R.id.profile_btn);
        Station = view.findViewById(R.id.station_btn);
        Staff = view.findViewById(R.id.staff_btn);
        Queue = view.findViewById(R.id.queue_btn);
        ContactUs = view.findViewById(R.id.contact_us_btn);
        Privacy = view.findViewById(R.id.policy_btn);


        if(getArguments() != null){
            username = getArguments().getString("username");
            type = getArguments().getString("type");

            if(type.matches("user")){
                Station.setVisibility(View.GONE);
                Staff.setVisibility(View.GONE);
                Queue.setVisibility(View.GONE);
            } else if(type.matches("staff")){
                Station.setVisibility(View.GONE);
                Staff.setVisibility(View.GONE);
            } else {
                Station.setVisibility(View.VISIBLE);
                Staff.setVisibility(View.VISIBLE);
                Queue.setVisibility(View.VISIBLE);
                Profile.setVisibility(View.VISIBLE);
            }
            System.out.println(username);
            System.out.println(type);

        }

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditProfileUI.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        Station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StationCrudUI.class);
                startActivity(i);
            }
        });

        Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StaffCrudUI.class);
                startActivity(i);
            }
        });

        Queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QueueCrudUI.class);
                startActivity(i);
            }
        });

        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ContactUsUI.class);
                startActivity(i);
            }
        });

        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PrivacyAndPolicy.class);
                startActivity(i);
            }
        });

        return view;
    }
}