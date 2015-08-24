package tutosandroidfrance.com.toolbaranddrawer.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tutosandroidfrance.com.toolbaranddrawer.R;


public class Fragment_Home extends  Fragment{
	Context context;
	public Fragment_Home(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        context=getActivity();
       
        return rootView;
    }
}
