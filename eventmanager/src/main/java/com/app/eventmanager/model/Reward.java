package com.app.eventmanager.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reward")
public class Reward {
	
	@Id
	@GeneratedValue
	@Column(name = "id_reward")
	private Long idReward;
	@Column(name = "reward_code")
	private String rewardCode;
	@Column(name = "type")
	private String type;
	@Column(name = "status")
	private Integer status;
	@Column(name = "voucher_code")
	private Integer voucherCode;
	
	@ManyToMany(mappedBy = "reward") 
    private Set<Event> event = new HashSet<>();
	
	public Reward() {

	}
	
	public Reward(Long idReward, String rewardCode, String type, int status, int voucherCode) {
		this.idReward = idReward;
		this.rewardCode = rewardCode;
		this.type = type;
		this.status = status;
		this.voucherCode = voucherCode;
	}

	public Long getId() {
		return idReward;
	}

	public void setId(Long id) {
		this.idReward = id;
	}

	public String getReward_code() {
		return rewardCode;
	}

	public void setReward_code(String rewardCode) {
		this.rewardCode = rewardCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVoucher_code() {
		return voucherCode;
	}

	public void setVoucher_code(Integer voucherCode) {
		this.voucherCode = voucherCode;
	}
	
	

}
