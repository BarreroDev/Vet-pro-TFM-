import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-left-bar',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './left-bar.html',
  styleUrl: './left-bar.css',
  standalone: true
})
export class LeftBar {}
