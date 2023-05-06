package com.example.auth_test.requests;

import com.example.auth_test.model.statement.Block;
import com.example.auth_test.model.user.BaseOfTraining;
import com.example.auth_test.model.user.TrainingForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String course;
    private String institute;
    private String groupName;
    private TrainingForm trainingForm;
    private BaseOfTraining baseOfTraining;
    private String blockNumber;
}
