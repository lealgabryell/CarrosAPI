import { Component } from '@angular/core';
import { Carro } from '../../../models/carro';
import { Marca } from '../../../models/marca';
import { Proprietario } from '../../../models/proprietario';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-carroslist',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './carroslist.component.html',
  styleUrl: './carroslist.component.scss',
})
export class CarroslistComponent {
  lista: Carro[] = [];

  constructor() {
    let proprietario1 = new Proprietario();
    proprietario1.id = 1;
    proprietario1.idade = 22;
    proprietario1.nome = 'Myguell';

    let carro1 = new Carro();
    carro1.id = 1;
    carro1.nome = 'Fiesta';
    carro1.id = 1;
    carro1.valorFIPE = 30000.0;
    carro1.proprietarios = [proprietario1];

    let marca1 = new Marca();
    marca1.id = 1;
    marca1.nome = 'Ford';
    marca1.cnpj = 'cnpjFORD';
    marca1.carros = [carro1];

    carro1.marca = marca1;
    this.lista.push(carro1);
  }

  deleteById(carro: Carro) {
    this.lista.findIndex((x) => {
      return x.id == carro.id;
    });
  }
}
