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
                    <h1 class="heading-large">Se connecter</h1>
                  </div>
                  <div class="text-size-medium">Entrez votre email et votre mot de passe pour vous connecter</div>
                </div>
              </div>
              <div class="w-form">
                <form id="wf-form-Login" name="wf-form-Login" action="/lalexandra/authenticate/connect" data-name="Login" method="post">
                  <div class="form-content-wrapper">
                    <div class="margin-bottom margin-small">
                      <div class="margin-bottom margin-xxsmall"><label for="name-3" class="form-label">Adresse mail</label></div><input type="email" class="form-input is-last w-input" maxlength="256" name="mail_client" data-name="Login Email" placeholder="Email" id="Login-Email" required="">
                    </div>
                    <div class="margin-bottom margin-small">
                      <div class="margin-bottom margin-xxsmall"><label for="name-3" class="form-label">Mot de passe</label></div><input type="password" class="form-input is-last w-input" maxlength="256" name="password_client" data-name="Login Password" placeholder="Password" id="Login-Password" required="">
                    </div><input type="submit" value="Log in" data-wait="Please wait..." class="button w-button">
                  </div>
                </form>
             
                <div class="margin-top margin-medium">
                  <div class="text-center">
                    <div class="margin-bottom margin-xxsmall">
                      <div class="text-size-regular">Vous n'avez pas de compte ? <a href="/lalexandra/authenticate/sign-up" class="text-style-link">Inscrivez vous</a>
                      </div>
                    </div>
                    <div class="text-size-regular">
                      <a href="../account-pages/forgot-password.html" class="text-style-link">Mot de passe oublié</a>
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