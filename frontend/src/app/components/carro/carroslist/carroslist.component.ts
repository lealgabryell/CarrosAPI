import { Component } from '@angular/core';
import { Carro } from '../../../models/carro';

@Component({
  selector: 'app-carroslist',
  standalone: true,
  imports: [],
  templateUrl: './carroslist.component.html',
  styleUrl: './carroslist.component.scss',
})
export class CarroslistComponent {
  lista: Carro[] = [];

  constructor() {}
}
