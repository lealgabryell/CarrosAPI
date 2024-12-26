import { Carro } from './carro';

export class Marca {
  id!: number;
  nome!: string;
  cnpj!: string;
  carros!: Carro[];
}
