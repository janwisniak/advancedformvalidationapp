package com.example.advancedformvalidationapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, phone, password, confirmPassword;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Powiązanie pól z widokiem
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(v -> {
            if (validateData()) {
                Toast.makeText(MainActivity.this, "Formularz został przesłany pomyślnie!", Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean validateData() {

        if (TextUtils.isEmpty(firstName.getText())) {
            firstName.setError("wprowadź imię");
            return false;
        }


        if (TextUtils.isEmpty(lastName.getText())) {
            lastName.setError("wprowadź nazwisko");
            return false;
        }


        if (TextUtils.isEmpty(email.getText()) || !isValidEmail(email.getText().toString())) {
            email.setError("niepoprawny adres email");
            return false;
        }

        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 9) {
            phone.setError("nr tel musi miec co najmiej 9 znakow");
            return false;
        }


        if (TextUtils.isEmpty(password.getText()) || password.getText().toString().length() < 6) {
            password.setError("Hasło musi miec co najmniej 6 znakow");
            return false;
        }


        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Hasła musza byc takie same");
            return false;
        }

        return true;
    }


    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return Pattern.matches(emailPattern, email);
    }
}