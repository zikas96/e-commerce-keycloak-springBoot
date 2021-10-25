import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakSecurityService } from 'src/app/core/services/keycloak-security.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public keycloakService: KeycloakSecurityService, private router: Router) { }

  ngOnInit(): void {
  }

  onLogout(){
    this.router.navigateByUrl('/');
    this.keycloakLogout();
  }

  keycloakLogout(){
    this.keycloakService.kc.logout();
  }

  onLogin(){
    this.keycloakService.kc.login();
  }

  onchangePassword(){
    this.keycloakService.kc.accountManagement();
  }

  isAppManager(){
    return this.keycloakService.kc.hasRealmRole('app-manager')
  }

}
