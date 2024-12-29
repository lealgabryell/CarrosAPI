import { Component } from '@angular/core';
import { Carro } from '../../../models/carro';
import { Marca } from '../../../models/marca';
import { Proprietario } from '../../../models/proprietario';
import { RouterLink } from '@angular/router';
import Swal from 'sweetalert2';

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
    carro1.valorFIPE = 30000.0;
    carro1.proprietarios = [proprietario1];

    let marca1 = new Marca();
    marca1.id = 1;
    marca1.nome = 'Ford';
    marca1.cnpj = 'cnpjFORD';
    marca1.carros = [];

    carro1.marca = marca1;
    this.lista.push(carro1);

    let carroNovo = history.state.carroNovo;
    let carroEditado = history.state.carroEditado;

    if (carroNovo) {
      carroNovo.id = this.lista.length + 1;
      this.lista.push(carroNovo)
    }

    if (carroEditado) {
      let indice = this.lista.findIndex((x) => {
        return x.id == carroEditado.id;
      });
      this.lista[indice] = carroEditado;
    }

  }

  deleteById(carro: Carro) {
    Swal.fire({
      title: 'Tem certeza que deseja deletar esse registro?',
      icon: 'warning',
      showConfirmButton: true,
      showDenyButton: true,
      denyButtonText: 'Cancelar',
      confirmButtonText: 'Deletar'
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        let indice = this.lista.findIndex((x) => {
          return x.id == carro.id;
        });
        this.lista.splice(indice, 1);

        Swal.fire({
          title: 'Sucesso!',
          text: 'Deletado com sucesso!',
          icon: 'success',
          confirmButtonText: 'Ok'
        })
      }
    });
  }
}
