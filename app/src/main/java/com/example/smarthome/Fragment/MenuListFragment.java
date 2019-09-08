
package com.example.smarthome.Fragment;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;

import com.example.smarthome.R;
//import com.google.android.material.navigation.NavigationView;
import com.example.smarthome.Utils.CircleTransformation;
import com.squareup.picasso.Picasso;

//import android.support.annotation.Nullable;
//import android.support.design.widget.NavigationView;
//import android.support.v4.app.Fragment;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

    private ImageView ivMenuUserProfilePhoto;
    private View selectView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        //tou xiang
        ivMenuUserProfilePhoto = (ImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
       // selectView = view.findViewById(R.id.select_view);
        //guan liang zheng ge caidianjiemian
        NavigationView vNavigation = view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@Nullable MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.menu_action:
//                        ActionFragment actionFragment = new ActionFragment();
//                        actionFragment.
//                    case R.id.menu_data:
//                    case R.id.menu_mode:
//                    case R.id.menu_safe:
//                    case R.id.menu_flower:
//
//                }
                Toast.makeText(getActivity(),menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        NavigationView vNavigation = (NavigationView) view.findViewById(R.id.vNavigation);
//        //zai zhe li xie dian ji shi jian
//        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                Toast.makeText(getActivity(),menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }) ;
        //setupHeader();
        return  view ;
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);
        Picasso.with(getActivity())
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);
    }

}
