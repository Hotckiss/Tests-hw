package hse.kirilenko.objects;

import hse.kirilenko.elements.WebButton;
import hse.kirilenko.elements.WebTextInput;

public class NewUserForm {
    private final WebButton ok;
    private final WebButton cancel;
    private final WebTextInput loginTextInput;
    private final WebTextInput passwordTextInput;
    private final WebTextInput confirmTextInput;

    NewUserForm(WebButton ok,
                WebButton cancel,
                WebTextInput loginTextInput,
                WebTextInput passwordTextInput,
                WebTextInput confirmTextInput) {
        this.ok = ok;
        this.cancel = cancel;
        this.loginTextInput = loginTextInput;
        this.passwordTextInput = passwordTextInput;
        this.confirmTextInput = confirmTextInput;
    }

    public void setLogin(String login) {
        loginTextInput.setText(login);
    }

    public void setPassword(String password) {
        passwordTextInput.setText(password);
    }

    public void setPasswordConfirm(String passwordConfirm) {
        confirmTextInput.setText(passwordConfirm);
    }

    public void ok() {
        ok.click();
    }

    public void cancel() {
        cancel.click();
    }
}