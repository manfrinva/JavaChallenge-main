package jobsity.bowling.service.impl;

import jobsity.bowling.service.FrameService;
import java.util.ArrayList;
import java.util.Arrays;
import jobsity.bowling.service.dto.ThrowDTO;
import java.util.List;
import jobsity.bowling.service.dto.FrameDTO;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

  @Mock
  private FrameService frameService = Mockito.mock(FrameService.class);

  @InjectMocks
  private ValidationServiceImpl validationService;

  @Before
  public void setup() {
    this.validationService = new ValidationServiceImpl(frameService);
  }

  @Test
  public void isValidFrame() {
    FrameDTO nullFrame = null;
    FrameDTO nullTrows = new FrameDTO();

    List<ThrowDTO> emptyTs = new ArrayList<ThrowDTO>(0);
    FrameDTO emptyThrows = new FrameDTO();
    emptyThrows.setThrowz(emptyTs);

    FrameDTO withoutNumber = new FrameDTO();

    List<ThrowDTO> strikeThrows = Arrays.asList(new ThrowDTO[]{ new ThrowDTO("10",ThrowDTO.FIRST_THROW)});
    FrameDTO strike = new FrameDTO(FrameDTO.FIRST_FRAME, strikeThrows);

    List<ThrowDTO> sparkThrows = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO("8",ThrowDTO.FIRST_THROW),
        new ThrowDTO("2",ThrowDTO.SECOND_THROW)});
    FrameDTO spark = new FrameDTO(FrameDTO.FIRST_FRAME, sparkThrows);

    List<ThrowDTO> openThrows = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO("8",ThrowDTO.FIRST_THROW),
        new ThrowDTO("0",ThrowDTO.SECOND_THROW)});

    FrameDTO openFrame = new FrameDTO(FrameDTO.FIRST_FRAME,openThrows);


    List<ThrowDTO> openThrowsFault = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO("8",ThrowDTO.FIRST_THROW),
        new ThrowDTO("F",ThrowDTO.SECOND_THROW)});

    FrameDTO openFrameFault = new FrameDTO(FrameDTO.FIRST_FRAME,openThrowsFault);

    List<ThrowDTO> badSparkTrows = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO("8",ThrowDTO.FIRST_THROW),
        new ThrowDTO("11",ThrowDTO.SECOND_THROW)});

    FrameDTO basSparkFrame = new FrameDTO(FrameDTO.FIRST_FRAME,badSparkTrows);


    List<ThrowDTO> lastFrameThrows = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO("8",ThrowDTO.FIRST_THROW),
        new ThrowDTO("10",ThrowDTO.SECOND_THROW),
        new ThrowDTO("10",ThrowDTO.THIRD_THROW)});

    FrameDTO lastFrame = new FrameDTO(FrameDTO.LAST_FRAME,lastFrameThrows);


    List<ThrowDTO> badLastFrameThrows = Arrays.asList(new ThrowDTO[]{
        new ThrowDTO(), new ThrowDTO(), new ThrowDTO()});

    FrameDTO badLastFrame = new FrameDTO(FrameDTO.LAST_FRAME,badLastFrameThrows);

    FrameDTO frameOut = new FrameDTO(11,strikeThrows);


    Assert.assertFalse(this.validationService.isValidFrame(nullFrame));
    Assert.assertFalse(this.validationService.isValidFrame(nullTrows));
    Assert.assertFalse(this.validationService.isValidFrame(emptyThrows));
    Assert.assertFalse(this.validationService.isValidFrame(withoutNumber));

    Mockito.when(frameService.isStrike(strike)).thenReturn(Boolean.TRUE);
    Assert.assertTrue(this.validationService.isValidFrame(strike));

    Mockito.when(frameService.isSparkFrame(spark)).thenReturn(Boolean.TRUE);
    Assert.assertTrue(this.validationService.isValidFrame(spark));

    Mockito.when(frameService.isOpenFrame(openFrame)).thenReturn(Boolean.TRUE);
    Assert.assertTrue(this.validationService.isValidFrame(openFrame));

    Mockito.when(frameService.isOpenFrame(openFrameFault)).thenReturn(Boolean.TRUE);
    Assert.assertTrue(this.validationService.isValidFrame(openFrameFault));


    Assert.assertFalse(this.validationService.isValidFrame(basSparkFrame));

    Mockito.when(frameService.isLastFrame(lastFrame.getNumber())).thenReturn(Boolean.TRUE);
    Assert.assertTrue(this.validationService.isValidFrame(lastFrame));

    Mockito.when(frameService.isLastFrame(badLastFrame.getNumber())).thenReturn(Boolean.TRUE);
    Assert.assertFalse(this.validationService.isValidFrame(badLastFrame));

    Assert.assertFalse(this.validationService.isValidFrame(frameOut));
  }

  @Test
  public void isValidThrow() {
    ThrowDTO nullThrow = null;
    ThrowDTO afterTen = new ThrowDTO("11",ThrowDTO.FIRST_THROW);
    ThrowDTO beforezero = new ThrowDTO("-1",ThrowDTO.FIRST_THROW);
    ThrowDTO ok = new ThrowDTO("10",ThrowDTO.FIRST_THROW);

    Assert.assertFalse(this.validationService.isValidThrow(nullThrow));
    Assert.assertFalse(this.validationService.isValidThrow(afterTen));
    Assert.assertFalse(this.validationService.isValidThrow(beforezero));
    Assert.assertTrue(this.validationService.isValidThrow(ok));
  }

}