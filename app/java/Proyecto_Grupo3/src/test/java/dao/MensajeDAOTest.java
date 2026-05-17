package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexionBBDD.ConexionBBDD;
import modelo.Mensaje;

public class MensajeDAOTest {

    private ConexionBBDD mockConexionBBDD;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MensajeDAO mensajeDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConexionBBDD = mock(ConexionBBDD.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConexionBBDD.getConexion()).thenReturn(mockConnection);
        mensajeDAO = new MensajeDAO(mockConexionBBDD);
    }

    @Test
    public void testCreate_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean resultado = mensajeDAO.create(1, 2, "Contenido del mensaje", "Asunto Test");

        assertTrue(resultado);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 2);
        verify(mockPreparedStatement).setString(3, "Contenido del mensaje");
        verify(mockPreparedStatement).setString(4, "Asunto Test");
    }

    @Test
    public void testCreate_Exception() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Error en DB"));

        boolean resultado = mensajeDAO.create(1, 2, "Error", "Error");

        assertFalse(resultado);
    }

    @Test
    public void testReadMensajesUsuario_Exito() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        
        when(mockResultSet.getInt("id_usuario")).thenReturn(2);
        when(mockResultSet.getInt("id_consulta")).thenReturn(1);
        when(mockResultSet.getString("asunto")).thenReturn("Test");
        when(mockResultSet.getString("descripcion")).thenReturn("Hola");

        ArrayList<Mensaje> resultado = mensajeDAO.readMensajesUsuario(2, 1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Test", resultado.get(0).getAsunto());
    }

    @Test
    public void testReadMensaje_Encontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        
        when(mockResultSet.getInt("id_usuario")).thenReturn(4);
        when(mockResultSet.getInt("id_consulta")).thenReturn(8);
        when(mockResultSet.getString("asunto")).thenReturn("Asunto Único");
        when(mockResultSet.getString("descripcion")).thenReturn("Cuerpo");

        Mensaje resultado = mensajeDAO.readMensaje(100);

        assertNotNull(resultado);
        assertEquals(4, resultado.getIdUsuario());
        assertEquals(8, resultado.getIdConsulta());
    }

    @Test
    public void testReadMensaje_NoEncontrado() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Mensaje resultado = mensajeDAO.readMensaje(999);

        assertNull(resultado);
    }
}