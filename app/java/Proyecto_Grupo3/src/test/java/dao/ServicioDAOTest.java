package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import conexionBBDD.ConexionBBDD;
import modelo.Servicio;

public class ServicioDAOTest {

    private ConexionBBDD mockConexionBBDD;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private ServicioDAO servicioDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConexionBBDD = mock(ConexionBBDD.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConexionBBDD.getConexion()).thenReturn(mockConnection);
        servicioDAO = new ServicioDAO(mockConexionBBDD);
    }

    @Test
    @DisplayName("Éxito: Leer todos los servicios disponibles")
    public void testReadAll_Exito() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_servicio")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("Mantenimiento");

        ArrayList<Servicio> resultado = servicioDAO.readAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Mantenimiento", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("Excepción: Fallo al leer todos los servicios devuelve null")
    public void testReadAll_Exception() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenThrow(new SQLException("Error"));

        ArrayList<Servicio> resultado = servicioDAO.readAll();

        assertNull(resultado);
    }

    @Test
    @DisplayName("Éxito: Encontrar un servicio por su ID")
    public void testRead_Encontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id_servicio")).thenReturn(2);

        Servicio resultado = servicioDAO.read(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getIdServicio());
    }

    @Test
    @DisplayName("Éxito: Borrar un servicio existente")
    public void testBorrarServicio_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = servicioDAO.borrarServicio(10);

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 10);
    }

    @Test
    @DisplayName("Éxito: Cambiar el estado de un servicio")
    public void testCambiarEstadoServicio_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = servicioDAO.cambiarEstadoServicio(10, true);

        assertTrue(resultado);
        verify(mockPreparedStatement).setBoolean(1, true);
        verify(mockPreparedStatement).setInt(2, 10);
    }

    @Test
    @DisplayName("Éxito: Filtrar servicios usando parámetros")
    public void testFiltrar_ConParametros() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_servicio")).thenReturn(15);

        ArrayList<Servicio> resultado = servicioDAO.filtrar(true, "Delegación Madrid", null, null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(15, resultado.get(0).getIdServicio());
    }
}