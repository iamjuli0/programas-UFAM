## Copyright (C) 2024 julio
##
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU General Public License as published by
## the Free Software Foundation, either version 3 of the License, or
## (at your option) any later version.
##
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <https://www.gnu.org/licenses/>.

## -*- texinfo -*-
## @deftypefn {} {@var{retval} =} codigosImpressao (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: julio <julio@JULIOS-COMPUTAD>
## Created: 2024-09-28

function saida = codigosImpressao (mensagens, mGeradora)
  [linhas, colunas] = size(mensagens);
  saida = [];

  for i = 1:linhas
    temp = [];

    for j = 1:colunas
      num = str2num(mensagens(i,j));
      linhaAtual = mGeradora(j,:);
      linhaMultiplicada = num*linhaAtual;
      temp = [temp; linhaMultiplicada];
      somas = sum(temp);
      res = mod(somas, 2);

    endfor

    saida = [saida; res];

  endfor

  saida = num2str(saida);
  saida = strrep(saida, ' ', '');

endfunction
