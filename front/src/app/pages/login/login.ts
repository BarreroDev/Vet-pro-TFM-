import { Component } from '@angular/core';
import {newVet} from '../../components/sing-up/new-vet'
import {RouterLink} from '@angular/router';



@Component({
  selector: 'app-login',
  imports: [newVet, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class login {


}

