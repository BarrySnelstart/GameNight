package nl.novi.gamenight.Services;
import nl.novi.gamenight.Model.Manual;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ManualRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManualServiceTest {


    @Mock
    private ManualRepository manualRepository;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private ManualService manualService;

    @BeforeEach
    public void setUp() {
        manualService = new ManualService(manualRepository, gameRepository);
    }

//    @Test
//    public void testManualUpload() throws IOException {
//        // Mock data
//
//        Game game = new Game();
//        game.setGameID(1l);
//        when(gameRepository.getReferenceById(1L)).thenReturn(game);
//
//        MultipartFile multipartFile = new MockMultipartFile("test.pdf", "test.pdf", "application/pdf", "test data".getBytes());
//        when(manualRepository.save(multipartFile)).thenReturn(multipartFile);
//        // Call the method
//        String uploadedManualName = manualService.manualUpload(multipartFile, 1L);
//        // Verify interactions and assertions
////        verify(manualRepository, times(1)).save(any());
////        verify(gameRepository, times(1)).save(any(Game.class));
//        assertEquals("test.pdf", uploadedManualName);
//    }

    @Test
    public void testManualDownload() throws IOException {
        // Mock data
        long manualId = 1L;
        byte[] compressedData = "test data".getBytes();
        Manual manual = new Manual();
        manual.setManualID(1L);
        manual.setData(compressedData);
        when(manualRepository.findById(manualId)).thenReturn(Optional.of(manual));

        // Call the method
        byte[] downloadedData = manualService.manualDownload(manualId);

        // Verify interactions and assertions
        verify(manualRepository, times(1)).findById(manualId);
        assertEquals(compressedData, downloadedData);
    }
}