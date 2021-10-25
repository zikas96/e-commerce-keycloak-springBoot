import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { KeycloakSecurityService } from './keycloak-security.service';

@Injectable()
export class KeycloakHttpInterceptorServiceService implements HttpInterceptor {

  constructor(private keycloakService: KeycloakSecurityService){ }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Http Interceptor call's ....")
    if(!this.keycloakService.kc.authenticated) return next.handle(req);

    let request = req.clone({
      setHeaders: {
        Authorization: 'Bearer ' + this.keycloakService.kc.token
      }
    })
    return next.handle(request);
  }
}
