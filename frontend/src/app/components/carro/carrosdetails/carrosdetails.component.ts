import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { Carro } from '../../../models/carro';
import { ActivatedRoute, Router } from '@angular/router';
import { Proprietario } from '../../../models/proprietario';
import { Marca } from '../../../models/marca';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-carrosdetails',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './carrosdetails.component.html',
  styleUrl: './carrosdetails.component.scss',
})
export class CarrosdetailsComponent {

  @Input("carro") carro: Carro = new Carro();
  @Output("retorno") retorno = new EventEmitter<any>();

  //Esse router serve para pegar o parametro de rota
  router = inject(ActivatedRoute);

  //serve para redirecionamento
  router2 = inject(Router);
  constructor() {
    let id = this.router.snapshot.params['id'];
    if (id > 0) {
      this.findById(id);
    }
  }
  findById(id: number) {
    //busca no backend
    let carroRetornado: Carro = new Carro();
    let proprietario1 = new Proprietario();
    proprietario1.id = 1;
    proprietario1.idade = 22;
    proprietario1.nome = 'Myguell';

    carroRetornado.id = 1;
    carroRetornado.nome = 'Fiesta';
    carroRetornado.valorFIPE = 30000.0;
    carroRetornado.proprietarios = [proprietario1];

    let marca1 = new Marca();
    marca1.id = 1;
    marca1.nome = 'Ford';
    marca1.cnpj = 'cnpjFORD';
    marca1.carros = [];

    carroRetornado.marca = marca1;
    this.carro = carroRetornado;
  }

  save() {
    if (this.carro.id > 0) {

      Swal.fire({
        title: 'Sucesso!',
        text: 'Editado com sucesso!',
        icon: 'success',
        confirmButtonText: 'Ok'
      })
      this.router2.navigate(['/admin/carros'], { state: { carroEditado: this.carro } })
    } else {
      Swal.fire({
        title: 'Sucesso!',
        text: 'Salvo com sucesso',
        icon: 'success',
        confirmButtonText: 'Ok'
      })
      this.router2.navigate(['/admin/carros'], { state: { carroNovo: this.carro } })
    }
    this.retorno.emit(this.carro)
  }
}
