import { Component, inject, OnInit } from '@angular/core';
import { Router, NavigationEnd, RouterLink } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './top-bar.html',
  styleUrl: './top-bar.css'
})
export class TopBar implements OnInit {
  private router = inject(Router);

  title: string = '';
  isMenuOpen: boolean = false;

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

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  closeMenu(): void {
    this.isMenuOpen = false;
  }

  logout(): void {
    this.closeMenu();
    this.router.navigate(['/login']);
  }
}
