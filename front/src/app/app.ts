import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {TopBar} from './components/top-bar/top-bar';
import {newVet} from './components/sing-up/new-vet';
import {login} from './pages/login/login';
import {NewAppointment} from './components/new-appointment/new-appointment';
import {NewOwner} from './components/new-owner/new-owner';
import {NewPet} from './components/new-pet/new-pet';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TopBar, login, newVet, NewAppointment, NewOwner, NewPet,],
  templateUrl: './app.html',
  styleUrl: './app.css',

})
export class App {
  protected readonly title = signal('front');
}
