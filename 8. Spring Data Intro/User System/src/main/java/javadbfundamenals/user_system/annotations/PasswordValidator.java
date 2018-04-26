package javadbfundamenals.user_system.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

   private boolean containsDigits;
   private boolean containsUppercase;
   private boolean containsLowercase;
   private boolean containsSpecialSymbols;
   private int minLength;
   private int maxLength;

   public void initialize(Password password) {
      this.containsDigits = password.containsDigit();
      this.containsUppercase = password.containsUppercase();
      this.containsLowercase = password.containsLowercase();
      this.containsSpecialSymbols = password.containsSpecialSymbol();
      this.minLength = password.minLength();
      this.maxLength = password.maxLength();
   }

   public boolean isValid(String password, ConstraintValidatorContext context) {

      if (password.length() < this.minLength || password.length() > this.maxLength) {
         return false;
      }

      Pattern lowerLetter = Pattern.compile("[a-z]");
      Matcher matcherLower = lowerLetter.matcher(password);
      if (!matcherLower.find() && this.containsLowercase) {
         return false;
      }

      Pattern upperLetter = Pattern.compile("[A-Z]");
      Matcher matcherUpper = upperLetter.matcher(password);
      if (!matcherUpper.find() && this.containsUppercase) {
         return false;
      }

      Pattern number = Pattern.compile("[0-9]");
      Matcher matcherNumber = number.matcher(password);
      if (!matcherNumber.find() && this.containsDigits) {
         return false;
      }

      Pattern symbol = Pattern.compile("[!@#$%^&*()_+<>?]");
      Matcher matcherSymbol = symbol.matcher(password);
      if (!matcherSymbol.find() && this.containsSpecialSymbols) {
         return false;
      }

      return true;
   }
}

