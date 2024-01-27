package com.example.splitwise.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.splitwise.controllers.SettleUpController;
import com.example.splitwise.dtos.SettleUpUserRequestDTO;
import com.example.splitwise.dtos.SettleUpUserResponseDTO;

@Component
public class SettleUpCommand implements Commands{

	private SettleUpController settleUpController;
    @Autowired
    SettleUpCommand(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        if(words.size() == 2 && words.get(1).equals("SettleUp")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDTO request = new SettleUpUserRequestDTO();
        request.setUserId(userId);

        SettleUpUserResponseDTO responseDTO = settleUpController.settleUpUser(request);
    }

}
