package com.example.libraryofpeers.view;

import Entities.User;
import Enums.ObjectTypeEnum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.ImageUtils;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    TextView navUserName;
    ImageView navImageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.getMenu().findItem(R.id.menuLogout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                LoginServiceProxy.getInstance().setCurrentUser(null);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            }
        });


        final TextView textTitle = findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());

            }
        });

        // Set Navigation User Information
        User user = LoginServiceProxy.getInstance().getCurrentUser();
        navUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navUserName);
        navImageProfile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.navImageProfile);
        String userFullName = user.getFirstName() + " " + user.getLastName();
        navUserName.setText(userFullName);
        navImageProfile.setImageDrawable(ImageUtils.drawableFromUrl(user.getImageUrl(), ObjectTypeEnum.user, getBaseContext()));
    }
}
