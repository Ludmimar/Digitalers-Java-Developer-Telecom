package com.educacionit.desafio02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.educacionit.desafio02.entidades.Cliente;
import com.educacionit.desafio02.entidades.Cuenta;
import com.educacionit.desafio02.entidades.Documento;
import com.educacionit.desafio02.entidades.Producto;
import com.educacionit.desafio02.entidades.TarjetaCredito;
import com.educacionit.desafio02.enumerados.TipoCuenta;
import com.educacionit.desafio02.enumerados.TipoDocumento;
import com.educacionit.desafio02.excepciones.TipoProductoExcepcion;
import com.educacionit.desafio02.utilidades.Fechas;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws TipoProductoExcepcion {
		// declaramos variables y objetos
		Scanner teclado = new Scanner(System.in);
		TipoDocumento tipoDocumento;
		String numeroDocumento;
		Documento documento;
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		List<Producto> productosCliente1 = new ArrayList<>();
		List<Producto> productosCliente2 = new ArrayList<>();
		Map<Documento, Cliente> clientes = new HashMap<>();
		try {

			cliente1.setDocumento(new Documento(TipoDocumento.CI, "01"));
			cliente1.setDescripcion("Cliente 01");
			cliente1.setFechaNacimiento(Fechas.getLocalDate("1983-03-15"));
			productosCliente1.add(new Cuenta(15, 2, TipoCuenta.CC));
			productosCliente1.add(new Cuenta(16, 4, TipoCuenta.CC));
			productosCliente1.add(new Cuenta(125, 9, TipoCuenta.CA));
			productosCliente1.add(new TarjetaCredito(2, 15, 8));
			productosCliente1.add(new TarjetaCredito(87, 74, 89));
			cliente1.setProductos(productosCliente1);

			cliente2.setDocumento(new Documento(TipoDocumento.DNI, "01"));
			cliente2.setDescripcion("Cliente 02");
			cliente2.setFechaNacimiento(Fechas.getLocalDate("1999-04-01"));
			productosCliente2.add(new Cuenta(15, 2, TipoCuenta.CC));
			productosCliente2.add(new Cuenta(105, 42, TipoCuenta.CA));
			productosCliente2.add(new Cuenta(150, 33, TipoCuenta.CA));
			productosCliente2.add(new TarjetaCredito(65, 52, 9));
			cliente2.setProductos(productosCliente2);

			clientes.put(cliente1.getDocumento(), cliente1);
			clientes.put(cliente2.getDocumento(), cliente2);
			System.out.println("Sistema de busqueda Bancario:");

			while (true) {
				try {
					System.out.print("Ingrese el tipo de documento:");
					tipoDocumento = TipoDocumento.valueOf(teclado.next().toUpperCase());
					break;
				} catch (Exception e) {
					System.out.println(
							"Tipo de Documento invalido solo se permiten " + Arrays.toString(TipoDocumento.values()));
				}
			}

			System.out.print("Ingrese el numero de documento:");
			numeroDocumento = teclado.next();

			documento = new Documento(tipoDocumento, numeroDocumento);

			Cliente cliente = clientes.get(documento);

			if (cliente != null) {
				System.out.println(cliente.getDescripcion());
				System.out.println(cliente.getDocumento());
				System.out.println(Fechas.getStringLocalDate(cliente.getFechaNacimiento()));
				System.out.println("Productos:");
				for (Producto producto : cliente.getProductos()) {
					System.out.println("\t" + producto);
				}
			} else {
				System.out.println("Cliente no existe en nuestro sistema");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			teclado.close();
		}

	}
}
