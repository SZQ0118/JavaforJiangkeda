package com.neusoft.View;

import com.neusoft.domain.Business;

public interface BusinessView {
        public void listAllBusinesses();
        public void selectBusiness();
        public void saveBusiness();
        public void removeBusiness();

        public Business login();
        public void showBusiness(Integer businessId);
        public void updatePassword(Integer businessId);
        public void updateBusiness(Business business);
}
