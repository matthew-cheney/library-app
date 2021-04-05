package com.example.libraryofpeers.view;

import android.content.Intent;
import android.os.Bundle;

import Config.Constants;
import Entities.User;
import Enums.ObjectTypeEnum;
import Request.EditUserRequest;
import Response.EditUserResponse;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.EditProfileTask;
import com.example.libraryofpeers.presenters.EditProfilePresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.utilities.ImageUtils;
import com.example.libraryofpeers.view.utils.SearchCache;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements EditProfileTask.EditProfileRequestObserver {
    View view;
    TextView userNameTextView;
    EditText userNameEditText;
    TextView firstNameTextView;
    EditText firstNameEditText;
    TextView lastNameTextView;
    EditText lastNameEditText;
    TextView emailTextView;
    EditText emailEditText;
    TextView phoneNumberTextView;
    EditText phoneNumberEditText;
    TextView imageTextView;
    EditText imageEditText;
    Button addImageBtn;
    TextView passwordTextView;
    EditText passwordEditText;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = LoginServiceProxy.getInstance().getCurrentUser();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                SearchCache.setCatalogQuery("");
                Intent mainIntent = new Intent(getContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        final ToggleButton editProfileBtn = (ToggleButton) view.findViewById(R.id.editProfileBtn);
        Button saveProfileBtn = (Button) view.findViewById(R.id.saveProfileBtn);

        userNameTextView = (TextView) view.findViewById(R.id.userNameText);
        userNameEditText = (EditText) view.findViewById(R.id.userNameEditor);
        firstNameTextView = (TextView) view.findViewById(R.id.firstNameText);
        firstNameEditText = (EditText) view.findViewById(R.id.firstNameEditor);
        lastNameTextView = (TextView) view.findViewById(R.id.lastNameText);
        lastNameEditText = (EditText) view.findViewById(R.id.lastNameEditor);
        emailTextView = (TextView) view.findViewById(R.id.emailText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditor);
        phoneNumberTextView = (TextView) view.findViewById(R.id.phoneNumberText);
        phoneNumberEditText = (EditText) view.findViewById(R.id.phoneNumberEditor);
        imageTextView = (TextView) view.findViewById(R.id.imageText);
        imageEditText = (EditText) view.findViewById(R.id.imageEditor);
        passwordTextView = (TextView) view.findViewById(R.id.passwordText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditor);

        initializeFields(user);

        addImageBtn = (Button) view.findViewById(R.id.addProfileImageButton);
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartImageSelectorActivity();
            }
        });

        editProfileBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                System.out.println(isChecked);
                if (isChecked) {
                    isEditingNow();
                } else {
                    isEditingDone();
                }
            }
        });

        saveProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUpdatedProfile();
            }
        });

        return view;
    }

    private void initializeFields(User user) {
        userNameTextView.setText(user.getUsername());
        userNameEditText.setText(user.getUsername());
        firstNameTextView.setText(user.getFirstName());
        firstNameEditText.setText(user.getFirstName());
        lastNameTextView.setText(user.getLastName());
        lastNameEditText.setText(user.getLastName());
        emailTextView.setText(user.getEmail());
        emailEditText.setText(user.getEmail());
        phoneNumberTextView.setText(user.getPhoneNumber());
        phoneNumberEditText.setText(user.getPhoneNumber());
        imageTextView.setText(user.getImageUrl());
        imageEditText.setText(user.getImageUrl());
        passwordTextView.setText("");
        passwordEditText.setText("");
    }

    private void isEditingNow() {
        userNameTextView.setVisibility(View.GONE);
        userNameEditText.setVisibility(View.VISIBLE);
        firstNameTextView.setVisibility(View.GONE);
        firstNameEditText.setVisibility(View.VISIBLE);
        lastNameTextView.setVisibility(View.GONE);
        lastNameEditText.setVisibility(View.VISIBLE);
        emailTextView.setVisibility(View.GONE);
        emailEditText.setVisibility(View.VISIBLE);
        phoneNumberTextView.setVisibility(View.GONE);
        phoneNumberEditText.setVisibility(View.VISIBLE);
        imageTextView.setVisibility(View.GONE);
        imageEditText.setVisibility(View.VISIBLE);
        passwordTextView.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.VISIBLE);

        addImageBtn.setVisibility(View.VISIBLE);
        addImageBtn.setEnabled(true);
    }

    private void isEditingDone() {
        userNameTextView.setVisibility(View.VISIBLE);
        userNameEditText.setVisibility(View.GONE);
        firstNameTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.GONE);
        lastNameTextView.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.GONE);
        emailTextView.setVisibility(View.VISIBLE);
        emailEditText.setVisibility(View.GONE);
        phoneNumberTextView.setVisibility(View.VISIBLE);
        phoneNumberEditText.setVisibility(View.GONE);
        imageTextView.setVisibility(View.VISIBLE);
        imageEditText.setVisibility(View.GONE);
        passwordTextView.setVisibility(View.VISIBLE);
        passwordEditText.setVisibility(View.GONE);

        addImageBtn.setVisibility(View.GONE);
        addImageBtn.setEnabled(false);
    }

    private void saveUpdatedProfile() {
        String userName = userNameEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String imageUrl = imageEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isUpdateValid = true;

        if (!user.getUsername().equals(userName)) {
            user.setUsername(userName);
        }
        if (!user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
        }
        if (!user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
        }
        if (!user.getEmail().equals(email)) {
            if (!email.matches(Constants.EMAIL_PATTERN)) {
                Toast.makeText(getActivity(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                isUpdateValid = false;
            } else {
                user.setEmail(email);
            }
        }
        if (!user.getPhoneNumber().equals(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }
        if (!user.getImageUrl().equals(imageUrl)) {
            user.setImageUrl(imageUrl);
        }
        if (!password.isEmpty()) {
            user.setPassword(password);
        }

        EditUserRequest request = new EditUserRequest(user);
        if (isUpdateValid) {
            EditProfilePresenter presenter = new EditProfilePresenter();
            EditProfileTask task = new EditProfileTask(this, presenter);
            task.execute(request);
        }
    }

    @Override
    public void onEditSuccess(EditUserResponse response, User user) {
        Toast.makeText(getActivity(), "Profile Updated!", Toast.LENGTH_LONG).show();
        String userFullName = user.getFirstName() + " " + user.getLastName();
        ((MainActivity) requireActivity()).navUserName.setText(userFullName);
        ((MainActivity) requireActivity()).navImageProfile.setImageDrawable(ImageUtils.drawableFromUrl(user.getImageUrl(), ObjectTypeEnum.user));
        Navigation.findNavController(view).navigate(R.id.action_menuProfile_to_menuHome);
    }

    @Override
    public void onEditFailure(EditUserResponse response) {
        Toast.makeText(getActivity(), "Profile update failed", Toast.LENGTH_LONG).show();
    }

    public void StartImageSelectorActivity() {
        Intent intent = new Intent(this.getActivity(), ImageSelectorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            imageEditText.setText(data.getStringExtra(Constants.IMAGE_URL_EXTRA));
            imageTextView.setText(data.getStringExtra(Constants.IMAGE_URL_EXTRA));
        }
    }
}