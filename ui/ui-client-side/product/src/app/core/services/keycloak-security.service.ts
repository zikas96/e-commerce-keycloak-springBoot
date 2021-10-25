import { Injectable } from '@angular/core';
import { KeycloakInstance, KeycloakProfile } from 'keycloak-js';
declare var Keycloak:any;

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {

  public kc: KeycloakInstance;
  public authUser: KeycloakProfile;

  constructor() { }

  async init(){
    console.log("start keycloak Servicee");
    this.kc = new Keycloak({
      url: 'http://localhost:8080/auth',
      realm: 'ecom-realm',
      clientId: 'angular-product-app'
    })

    await this.kc.init({
      //onLoad: 'login-required'
      onLoad: 'check-sso'
    })

    console.log(this.kc.token)
    if(this.kc.authenticated){
      this.authUser = await this.kc.loadUserProfile();
      console.log(this.authUser.username)
    }
  }

  public isManager(): boolean{
    return this.kc.hasResourceRole('app-manager');
  }
}
