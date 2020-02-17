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
	@Column(name = "id")
	private Integer id;
	@Column(name = "reward_code")
	private String reward_code;
	@Column(name = "type")
	private String type;
	@Column(name = "status")
	private Integer status;
	@Column(name = "voucher_code")
	private Integer voucher_code;
	
	@ManyToMany(mappedBy = "reward") 
    private Set<Event> event = new HashSet<>();
	
	public Reward() {

	}
	
	public Reward(int id, String reward_code, String type, int status, int voucher_code) {
		this.id = id;
		this.reward_code = reward_code;
		this.type = type;
		this.status = status;
		this.voucher_code = voucher_code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReward_code() {
		return reward_code;
	}

	public void setReward_code(String reward_code) {
		this.reward_code = reward_code;
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
		return voucher_code;
	}

	public void setVoucher_code(Integer voucher_code) {
		this.voucher_code = voucher_code;
	}
	
	

}
