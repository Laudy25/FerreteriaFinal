package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Kardex;
import com.example.FerreteriaFinal.modelos.Producto;
import com.example.FerreteriaFinal.modelos.Venta;
import com.example.FerreteriaFinal.repositorios.KardexRepositorio;
import com.example.FerreteriaFinal.repositorios.ProductoRepositorio;
import com.example.FerreteriaFinal.repositorios.VentaRepositorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private KardexRepositorio kardexRepositorio;

    @Autowired
    private VentaRepositorio ventaRepositorio;

    public byte[] generarReporte(String tipo) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            if ("kardex".equals(tipo)) {
                document.add(new Paragraph("Reporte de Kardex"));
                document.add(new Paragraph(" ")); 
                agregarTablaKardex(document);
            } else if ("productos".equals(tipo)) {
                document.add(new Paragraph("Reporte de Productos"));
                document.add(new Paragraph(" ")); 
                agregarTablaProductos(document);
            } else if ("ventas".equals(tipo)) {
                document.add(new Paragraph("Reporte de Ventas"));
                document.add(new Paragraph(" ")); 
                agregarTablaVentas(document);
            } else {
                document.add(new Paragraph("Tipo de reporte desconocido"));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void agregarTablaProductos(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        PdfPCell cell;
        cell = new PdfPCell(new Paragraph("Nombre"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Descripción"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Precio"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Stock"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        List<Producto> productos = productoRepositorio.findAll();
        for (Producto producto : productos) {
            table.addCell(producto.getNombre());
            table.addCell(producto.getDescripcion());
            table.addCell("Q" + producto.getPrecio());
            table.addCell(String.valueOf(producto.getStock()));
        }

        document.add(table);
    }

    private void agregarTablaKardex(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        PdfPCell cell;
        cell = new PdfPCell(new Paragraph("Producto"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Cantidad"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Tipo Movimiento"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Fecha"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Saldo"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        List<Kardex> movimientos = kardexRepositorio.findAll();
        for (Kardex movimiento : movimientos) {
            table.addCell(movimiento.getProductoNombre());
            table.addCell(String.valueOf(movimiento.getCantidad()));
            table.addCell(movimiento.getTipoMovimiento());
            table.addCell(movimiento.getFecha().toString());
            table.addCell(String.valueOf(movimiento.getSaldo()));
        }

        document.add(table);
    }

    private void agregarTablaVentas(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        PdfPCell cell;
        cell = new PdfPCell(new Paragraph("Cliente"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Productos"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Fecha"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Método de Pago"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        List<Venta> ventas = ventaRepositorio.findAll();
        for (Venta venta : ventas) {
            table.addCell(venta.getCliente());
            table.addCell(String.join(", ", venta.getProductos()));
            table.addCell(venta.getFecha().toString());
            table.addCell("Q" + venta.getTotal());
            table.addCell(venta.getMetodoPago());
        }

        document.add(table);
    }
}


