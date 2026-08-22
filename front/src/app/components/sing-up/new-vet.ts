import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-sing-up',
  imports: [],
  templateUrl: './new-vet.html',
  styleUrl: './new-vet.css',
  standalone: true
})


export class newVet {
  @Output() close = new EventEmitter<void>();

  onClose(): void{
    this.close.emit();
  }




}
