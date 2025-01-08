import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Carro } from '../../../models/carro';
import { RouterLink } from '@angular/router';
import Swal from 'sweetalert2';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { CarrosdetailsComponent } from '../carrosdetails/carrosdetails.component';
import { CarroService } from '../../../services/carro.service';

@Component({
  selector: 'app-carroslist',
  standalone: true,
  imports: [RouterLink, MdbModalModule, CarrosdetailsComponent],
  templateUrl: './carroslist.component.html',
  styleUrl: './carroslist.component.scss',
})
export class CarroslistComponent {
  lista: Carro[] = [];
  carroEdit: Carro = new Carro();

  carroService = inject(CarroService);
  //para abrir a modal
  modalService = inject(MdbModalService);
  @ViewChild('modalCarrosDetails') modalCarrosDetails!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;

  new() {
    this.carroEdit = new Carro();
    this.modalRef = this.modalService.open(this.modalCarrosDetails);
  }
  edit(carro: Carro) {
    this.carroEdit = Object.assign({}, carro); //clonando para evitar referencia de objeto, e ele nao ser alterado em tempo real, apenas quando o cliente emitir o evento
    this.modalRef = this.modalService.open(this.modalCarrosDetails)
  }
  retornoDetails(carro: Carro) {
    if (carro.id > 0) {
      let indice = this.lista.findIndex(x => { return x.id == carro.id })
      this.lista[indice] = carro;
    } else {
      carro.id = this.lista.length + 1
      this.lista.push(carro)
    }
    this.modalRef.close();
  }
  findAll() {
    this.carroService.findAll().subscribe({
      next: lista => { //quando o back retorna o que se espera
        this.lista = lista;

      },
      error: err => { //quando ocorrer qualquer erro(badrequest, exceptions...)
        alert('Ocorreu um erro!')
      }
    });
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
  constructor() {
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

}
