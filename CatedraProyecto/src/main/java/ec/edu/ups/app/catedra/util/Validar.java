package ec.edu.ups.app.catedra.util;

/**
 * 
 * @author Christian Flores
 */


public class Validar 
{
	public static final int NUM_PROVINCIAS = 24;
	private static int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	private static int constante = 11;
		
	public static Boolean validacionCedula(String cedula) {
		boolean isValid = false;
		if (cedula == null || cedula.length() != 10) {
			isValid = false;
		}
		final int prov = Integer.parseInt(cedula.substring(0, 2));

		if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
			isValid = false;
		}

		int[] d = new int[10];
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}

		int imp = 0;
		int par = 0;

		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}

		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}

		final int suma = imp + par;

		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)+ "0")- suma;

		d10 = (d10 == 10) ? 0 : d10;

		if (d10 == d[9]) {
			isValid = true;
		} else {
			isValid = false;
		}

		return isValid;
	}

	public static Boolean validaRucEP(String ruc) {
		final int prov = Integer.parseInt(ruc.substring(0, 2));
		boolean resp = false;

		if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
			resp = false;
		}

		// boolean val = false;
		Integer v1, v2, v3, v4, v5, v6, v7, v8, v9;
		Integer sumatoria;
		Integer modulo;
		Integer digito;
		int[] d = new int[ruc.length()];

		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(ruc.charAt(i) + "");
		}

		v1 = d[0] * 3;
		v2 = d[1] * 2;
		v3 = d[2] * 7;
		v4 = d[3] * 6;
		v5 = d[4] * 5;
		v6 = d[5] * 4;
		v7 = d[6] * 3;
		v8 = d[7] * 2;
		v9 = d[8];

		sumatoria = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
		modulo = sumatoria % 11;
		if (modulo == 0) {
			return true;
		}
		digito = 11 - modulo;

		if (digito.equals(v9)) {
			resp = true;
		} else {
			resp = false;
		}
		return resp;
	}

	public static Boolean validacionRUC(String ruc) {
		boolean resp_dato = false;
		final int prov = Integer.parseInt(ruc.substring(0, 2));
		if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
			resp_dato = false;
		}

		int[] d = new int[10];
		int suma = 0;

		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(ruc.charAt(i) + "");
		}

		for (int i = 0; i < d.length - 1; i++) {
			d[i] = d[i] * coeficientes[i];
			suma += d[i];
		}

		int aux, resp;

		aux = suma % constante;
		resp = constante - aux;

		resp = (aux == 0) ? 0 : resp;

		if (resp == d[9]) {
			resp_dato = true;
		} else {
			resp_dato = false;
		}
		return resp_dato;
	}


}
