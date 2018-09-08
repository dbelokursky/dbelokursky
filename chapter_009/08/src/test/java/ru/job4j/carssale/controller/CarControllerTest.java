package ru.job4j.carssale.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.job4j.carssale.domain.*;
import ru.job4j.carssale.service.CarService;
import ru.job4j.carssale.service.ImageService;
import ru.job4j.carssale.service.OwnerService;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private CarService carService;

    @Test
    @WithMockUser(username = "owner1")
    public void showCars() throws Exception {
        given(this.carService.findAll()).willReturn(new ArrayList<>());
        this.mockMvc.perform(get("/cars")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("carsList"));
        verify(this.carService, times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "owner1")
    public void showCarCardById() throws Exception {
        Transmission transmission = mock(Transmission.class);
        when(transmission.getName()).thenReturn("transmission");

        Suspension suspension = mock(Suspension.class);
        when(suspension.getName()).thenReturn("suspension");

        Engine engine = mock(Engine.class);
        when(engine.getName()).thenReturn("engine");

        Owner owner = mock(Owner.class);
        when(owner.getId()).thenReturn(1L);

        Car car = mock(Car.class);
        when(car.getOwner()).thenReturn(owner);
        when(car.getId()).thenReturn(1L);
        when(car.getTransmission()).thenReturn(transmission);
        when(car.getSuspension()).thenReturn(suspension);
        when(car.getEngine()).thenReturn(engine);

        given(this.carService.findById(1L)).willReturn(car);
        given(this.ownerService.findByLogin("owner1")).willReturn(owner);

        this.mockMvc.perform(get("/carcard?carId=1").accept(MediaType.TEXT_HTML)
        ).andExpect(status().isOk()
        ).andExpect((view().name("carCardOwner")));
        verify(this.carService, times(1)).findById(1L);
    }

    @Test
    @WithMockUser(username = "owner1")
    public void updateCarCard() throws Exception {
        this.mockMvc.perform(
                post("/carcard")
                        .with(csrf())
                        .param("id", "1")
                        .param("brand", "BMW")
                        .param("model", "X1")
                        .param("transmission.name", "manual")
                        .param("suspension.name", "depended")
                        .param("engine.name", "diesel")
                        .param("sold", "false"))
                .andExpect(status().is3xxRedirection());
        verify(this.carService, times(1)).save(
                Car.builder()
                        .id(1L)
                        .brand("BMW")
                        .model("X1")
                        .transmission(Transmission.builder().name("manual").build())
                        .suspension(Suspension.builder().name("depended").build())
                        .engine(Engine.builder().name("diesel").build())
                        .sold(false)
                        .build());
    }

    @Test
    @WithMockUser(username = "owner1")
    public void getCarAddPage() throws Exception {
        this.mockMvc.perform(get("/add")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("carAdd"));
    }

    @Test
    @WithMockUser(username = "owner1")
    public void addCar() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.multipart("/add")
                        .with(csrf())
                        .param("id", "1")
                        .param("brand", "BMW")
                        .param("model", "X1")
                        .param("transmission.name", "manual")
                        .param("suspension.name", "depended")
                        .param("engine.name", "diesel")
                        .param("sold", "false"))
                .andExpect(status().is3xxRedirection());
        verify(this.carService, times(1)).save(
                Car.builder()
                        .id(1L)
                        .brand("BMW")
                        .model("X1")
                        .transmission(Transmission.builder().name("manual").build())
                        .suspension(Suspension.builder().name("depended").build())
                        .engine(Engine.builder().name("diesel").build())
                        .sold(false)
                        .build());
    }

    @Test
    public void getLoginPage() throws Exception {
        this.mockMvc.perform(get("/login")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
}