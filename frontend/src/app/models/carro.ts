import { Marca } from './marca';
import { Proprietarios } from './proprietario';

export class Carro {
  id!: number;
  nome!: string;
  ano!: number;
  marca!: Marca;
  proprietarios!: Proprietarios[];
  valorFIPE!: number;
}
