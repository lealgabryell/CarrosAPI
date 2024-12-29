import { Component } from '@angular/core';
import { Marca } from '../../../models/marca';

@Component({
  selector: 'app-marcadetails',
  standalone: true,
  imports: [],
  templateUrl: './marcadetails.component.html',
  styleUrl: './marcadetails.component.scss'
})
export class MarcasdetailsComponent {

   lista: Marca[] = []; 

}
