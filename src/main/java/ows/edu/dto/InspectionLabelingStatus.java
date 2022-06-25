package ows.edu.dto;

import lombok.Data;

@Data
public class InspectionLabelingStatus {
  private int receiveItem;
  private int receiveItemQuantity;
  private int inspectionItem;
  private int inspectionItemQuantity;
  private int labelingItem;
  private int labelingItemQuantity;
  private int passedItem;
  private int passedItemQuantity;
  private int missingItem;
  private int missingItemQuantity;
  private int damagedItem;
  private int damagedItemQuantity;
}
