package com.sachtech.datingapp.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public  class BaseFragment {

    public static void replaceFragment(AppCompatActivity activity, int id, Fragment fragment, Bundle bundle)
    {
     // fragment=new Fragment();
      if(bundle!=null)
      fragment.setArguments(bundle);
      activity.getSupportFragmentManager().beginTransaction().replace(id,fragment).addToBackStack(null).commit();
    }

    public static void addFragment(int id, Fragment fragment, Bundle bundle)
    {
      fragment=new Fragment();
      if(bundle!=null)
      fragment.setArguments(bundle);
      fragment.getActivity().getSupportFragmentManager().beginTransaction().add(id,fragment).commit();
    }
}
