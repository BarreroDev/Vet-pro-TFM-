import { Component, inject, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  templateUrl: './top-bar.html',
  styleUrl: './top-bar.css'
})
export class TopBar implements OnInit {
  private router = inject(Router);

  title: string = '';

  ngOnInit(): void {
    this.updateTitle();

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.updateTitle();
    });
  }

  private updateTitle(): void {
    let currentRoute = this.router.routerState.root;

    while (currentRoute.firstChild) {
      currentRoute = currentRoute.firstChild;
    }
    this.title = currentRoute.snapshot.data['title'] || 'Veterinaria';
  }

  logout(): void {
    this.router.navigate(['/login']);
  }
}
