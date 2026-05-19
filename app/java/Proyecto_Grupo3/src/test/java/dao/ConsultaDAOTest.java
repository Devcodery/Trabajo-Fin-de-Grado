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
import modelo.Consulta;

public class ConsultaDAOTest {

    private ConexionBBDD mockConexionBBDD;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private ConsultaDAO consultaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConexionBBDD = mock(ConexionBBDD.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConexionBBDD.getConexion()).thenReturn(mockConnection);
        consultaDAO = new ConsultaDAO(mockConexionBBDD);
    }

    @Test
    @DisplayName("Éxito: Borrar consulta vinculada a un servicio")
    public void testBorrarServicioConsulta_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = consultaDAO.borrarServicioConsulta(1);

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 1);
    }

    @Test
    @DisplayName("Excepción: Fallo al intentar borrar consulta de un servicio")
    public void testBorrarServicioConsulta_Exception() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Error simulado"));

        boolean resultado = consultaDAO.borrarServicioConsulta(1);

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Éxito: Obtener todas las consultas de un cliente específico")
    public void testReadConsultaCliente_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_consulta")).thenReturn(10);
        when(mockResultSet.getString("estado")).thenReturn("Abierta");
        when(mockResultSet.getString("titulo")).thenReturn("Duda");

        ArrayList<Consulta> resultado = consultaDAO.readConsultaCliente(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getIdConsulta());
    }

    @Test
    @DisplayName("Éxito: Obtener todas las consultas de un servicio específico")
    public void testReadConsultaServicio_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_consulta")).thenReturn(15);
        when(mockResultSet.getString("estado")).thenReturn("Abierta");
        when(mockResultSet.getString("titulo")).thenReturn("Duda de servicio");

        ArrayList<Consulta> resultado = consultaDAO.readConsultaServicio(3);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(15, resultado.get(0).getIdConsulta());
        verify(mockPreparedStatement).setInt(1, 3);
    }

    @Test
    @DisplayName("Excepción: Fallo al buscar consultas de un servicio")
    public void testReadConsultaServicio_Exception() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Error simulado en DB"));

        ArrayList<Consulta> resultado = consultaDAO.readConsultaServicio(3);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Éxito: Obtener todas las consultas asignadas a un consultor")
    public void testReadConsultaConsultor_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_consulta")).thenReturn(20);

        ArrayList<Consulta> resultado = consultaDAO.readConsultaConsultor(2);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Éxito: Obtener la lista completa de todas las consultas")
    public void testReadAll_Exito() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);

        ArrayList<Consulta> resultado = consultaDAO.readAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Éxito: Encontrar una consulta específica por su ID")
    public void testRead_Encontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id_consulta")).thenReturn(5);

        Consulta resultado = consultaDAO.read(5);

        assertNotNull(resultado);
        assertEquals(5, resultado.getIdConsulta());
    }

    @Test
    @DisplayName("Fallo controlado: Buscar una consulta con un ID inexistente")
    public void testRead_NoEncontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Consulta resultado = consultaDAO.read(99);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Éxito: Filtrar consultas pasando múltiples parámetros")
    public void testFiltrar_ConFiltros() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("estadoActual")).thenReturn("En proceso");

        ArrayList<Consulta> resultado = consultaDAO.filtrar(1, null, "En proceso", null, null, null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Éxito: Actualizar el estado de una consulta")
    public void testUpdateEstado_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = consultaDAO.updateEstado(5, "Cerrada");

        assertTrue(resultado);
        verify(mockPreparedStatement).setString(1, "Cerrada");
        verify(mockPreparedStatement).setInt(2, 5);
    }

    @Test
    @DisplayName("Éxito: Reasignar un nuevo consultor a una consulta")
    public void testUpdateConsultor_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = consultaDAO.updateConsultor(5, 3);

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 3);
        verify(mockPreparedStatement).setInt(2, 5);
    }
}