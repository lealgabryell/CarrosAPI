import { Marca } from './marca';
import { Proprietario } from './proprietario';

export class Carro {
  id!: number;
  nome!: string;
  ano!: number;
  marca!: Marca;
  proprietarios!: Proprietario[];
  valorFIPE!: number;
}
