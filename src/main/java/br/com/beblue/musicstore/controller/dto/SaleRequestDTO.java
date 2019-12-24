package br.com.beblue.musicstore.controller.dto;

import javax.validation.constraints.Min;
import java.util.List;

public class SaleRequestDTO {

    @Min(1)
    private List<Integer> discsIds;

    public List<Integer> getDiscsIds() {
        return discsIds;
    }

    public void setDiscsIds(List<Integer> discsIds) {
        this.discsIds = discsIds;
    }

    @Override
    public String toString() {
        return "SaleRequestDTO{" +
                "discsIds=" + discsIds +
                '}';
    }
}
