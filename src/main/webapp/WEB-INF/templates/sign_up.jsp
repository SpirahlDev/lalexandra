<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<main class="main-wrapper">
    <section class="account-component">
      <div class="page-padding">
        <div class="container-large z-index-1">
          <div class="flex-vertical-center">
            <div class="max-width-medium">
              <div class="text-center">
                <div class="margin-bottom margin-medium">
                  <div class="margin-bottom margin-small">
                    <h1 class="heading-large">Créez un compte</h1>
                  </div>
                  <div class="text-size-medium">Créez un compte et devenez client de l'Alexandra</div>
                </div>
              </div>
              <div class="w-form">
                <form id="wf-form-Login" name="wf-form-Login" action="/lalexandra/authenticate/sign-up" data-name="Login" method="post">
                  <div class="form-content-wrapper">

                    <div class="margin-bottom margin-small" id="sign-up-form">
                      <div>
                        <div class="margin-bottom margin-xxsmall">
                          <label for="name-3" class="form-label">Nom</label>
                        </div>
                        <input type="text" class="form-input is-last w-input" maxlength="256" name="name_client" data-name="Name" placeholder="Votre nom" id="Name" required="">
                      </div>

                      <div>
                        <div class="margin-bottom margin-xxsmall">
                          <label for="name-3" class="form-label">Prénom</label>
                        </div>
                        <input type="text" class="form-input is-last w-input" maxlength="256" name="firstname_client" data-name="Sign up email" placeholder="Votre prénom" id="Sign-up-email" required="">
                      </div>
                    </div>

                    <div class="margin-bottom margin-small">
                      <div class="margin-bottom margin-xxsmall"><label for="name" class="form-label">E-mail</label></div><input type="email" class="form-input is-last w-input" maxlength="256" name="mail_client" data-name="" placeholder="Entrez votre adresse mail" id="Create-Password" required="">
                    </div>
                    <div class="margin-bottom margin-small">
                      <div class="margin-bottom margin-xxsmall"><label for="name" class="form-label">Numéro de téléphone</label></div><input type="tel" class="form-input is-last w-input" maxlength="20" name="phonenumber_client" placeholder="Entrez votre numéro de téléphone" id="Create-Password" required="">
                    </div>
                    <div class="margin-bottom margin-small">
                      <div class="margin-bottom margin-xxsmall"><label for="name" class="form-label">Mot de passe</label></div><input type="tel" class="form-input is-last w-input" maxlength="20" name="password_client" placeholder="Définissez un mot de passe " id="Create-Password" required="">
                    </div>
                    <div class="margin-bottom margin-small"><label id="Contact-6-Checkbox" class="w-checkbox form-checkbox margin-0">
                        <div class="w-checkbox-input w-checkbox-input--inputType-custom form-checkbox-icon"></div><input type="checkbox" name="Contact-6-Checkbox" id="Contact 6 Checkbox" data-name="Contact 6 Checkbox" required="" style="opacity:0;position:absolute;z-index:-1"><span for="Contact-6-Checkbox" class="form-checkbox-label w-form-label">I have read and agree to the <a href="../legal.html" class="text-style-link">Terms &amp; Conditions</a>.</span>
                      </label></div><input type="submit" value="Create account" data-wait="Please wait..." class="button w-button">
                  </div>
                </form>
               
                <div class="margin-top margin-medium">
                  <div class="text-center">
                    <div class="text-size-regular">Vous avez déja un compte ? <a href="/lalexandra/authenticate/sign-in" class="text-style-link">Se connecter</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div><img src="/lalexandra/statics/images/Graphic-Big.svg" loading="lazy" alt="" class="account-graphic"><img src="/lalexandra/statics/images/Graphic-Big.svg" loading="lazy" alt="" class="account-graphic-2">
    </section>
  </main>