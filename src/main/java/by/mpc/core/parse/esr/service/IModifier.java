package by.mpc.core.parse.esr.service;

import by.mpc.core.parse.esr.model.ListStation;

public interface IModifier {
	ListStation filter(ListStation listStation);
}
